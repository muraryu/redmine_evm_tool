package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		ArrayList<Ticket> ticketList = importTicketCsv(new File("evmutf.txt"));
		ArrayList<TimeLog> spentTimeRecordList = importTimeLogCsv(new File("timelog.csv"));
		/*
		 * for (Ticket ticket : ticketList) {
		 * System.out.println(ticket.getUpdateTime()); }
		 */
		// タイムライン生成
		ArrayList<EvmUnitData> evmUnitDataList = calcEvm(ticketList, spentTimeRecordList);
		
		// csv出力
		exportEvmData(evmUnitDataList, new File("evm.csv"));

	}

	private static void exportEvmData(ArrayList<EvmUnitData> evmUnitDataList, File file) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		bw.write("date,pv,ev,ac,bac,cv,sv,cpi,spi,eac,etc");
		bw.newLine();
		for(EvmUnitData evmUnitData : evmUnitDataList) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			bw.write(df.format(evmUnitData.getDate()) + "," + evmUnitData.getPv() + "," + evmUnitData.getEv() + "," + evmUnitData.getAc() + "," + evmUnitData.getBac() + "," + evmUnitData.getCv() + "," + evmUnitData.getSv() + "," + evmUnitData.getCpi() + "," + evmUnitData.getSpi() + "," + evmUnitData.getEac() + "," + evmUnitData.getEtc());
			bw.newLine();
		}
		
		bw.close();
		
	}

	/**
	 * プロジェクト期間のタイムライン生成
	 * 
	 * @param ticketList
	 * @return
	 */
	private static ArrayList<EvmUnitData> calcEvm(ArrayList<Ticket> ticketList, ArrayList<TimeLog> timeLogList) {

		// 引数チェック
		if (ticketList == null || ticketList.size() == 0) {
			System.exit(1);
		}

		// プロジェクト開始日と終了日を特定
		Date startDate = new Date(Long.MAX_VALUE);
		Date endDate = new Date(0);
		for (Ticket ticket : ticketList) {
			Date date;
			date = ticket.getStartDate();
			if (date != null && startDate.after(date)) {
				startDate = date;
			}
			date = ticket.getCloseDate();
			if (date != null && endDate.before(date)) {
				endDate = date;
			}
			date = ticket.getDueDate();
			if (date != null && endDate.before(date)) {
				endDate = ticket.getDueDate();
			}
		}
		for (TimeLog timeLog : timeLogList) {
			Date date = timeLog.getDate();
			if (date != null) {
				if (startDate.after(date)) {
					startDate = date;
				}
				if (endDate.before(date)) {
					endDate = date;
				}
			}
		}
		if (startDate.equals(new Date(Long.MAX_VALUE)) || endDate.equals(new Date(0))) {
			System.out.println("開始日、終了日、期日が未設定のためプロジェクト期間を特定できない");
			System.exit(2);
		}

		// 開始日から終了日までの日ごとのEVMデータを生成
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		ArrayList<EvmUnitData> evmUnitDataList = new ArrayList<EvmUnitData>();
		ArrayList<Ticket> ticketListPV = new ArrayList<Ticket>(ticketList);
		ArrayList<Ticket> ticketListEV = new ArrayList<Ticket>(ticketList);
		double pv = 0;
		double ev = 0;
		double ac = 0;
		double bac = 0;
		// ArrayList<Ticket> ticketListAC = new ArrayList<Ticket>(ticketList);

		// bac (and remove ticket which has no estimatedTime data)
		Iterator<Ticket> it = ticketList.iterator();
		while (it.hasNext()) {
			Ticket ticket = it.next();
			Double estimatedTime = ticket.getEstimatedTime();
			if (estimatedTime == null) {
				it.remove();
			} else {
				bac += ticket.getEstimatedTime();
			}
		}

		// pv,ev,ac
		for (Date date = startDate; date.before(endDate); calendar.add(Calendar.DAY_OF_MONTH, 1)) {
			date = calendar.getTime();

			// pv
			Iterator<Ticket> itPV = ticketListPV.iterator();
			while (itPV.hasNext()) {
				Ticket ticketPV = itPV.next();
				Date dueDate = ticketPV.getDueDate();
				Double estimatedTime = ticketPV.getEstimatedTime();
				if (dueDate == null || estimatedTime == null) {
					itPV.remove();
				} else if (dueDate.equals(date)) {
					pv += estimatedTime;
					itPV.remove();
				}
			}

			// ev
			Iterator<Ticket> itEV = ticketListEV.iterator();
			while (itEV.hasNext()) {
				Ticket ticketEV = itEV.next();
				Date closeDate = (Date) ticketEV.getCloseDate();
				Double estimatedTime = ticketEV.getEstimatedTime();
				String status = ticketEV.getStatus();
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				if (closeDate == null || estimatedTime == null || status == null) {
					itEV.remove();
				} else if (df.format(closeDate).equals(df.format(date)) && status.equals("完了")) {
					ev += estimatedTime;
					itEV.remove();
				}
			}

			// ac
			Iterator<TimeLog> itAC = timeLogList.iterator();
			while (itAC.hasNext()) {
				TimeLog timeLog = itAC.next();
				Date dateTimeLog = timeLog.getDate();
				Double spentTime = timeLog.getSpentTime();
				if (dateTimeLog == null || spentTime == null) {
					itAC.remove();
				} else if (dateTimeLog.equals(date)) {
					ac += timeLog.getSpentTime();
					itAC.remove();
				}
			}
			
			System.out.println(calendar.getTime() + "\t" + pv + "\t" + ev + "\t" + ac + "\t" + bac);
			evmUnitDataList.add(new EvmUnitData(date, pv, ev, ac, bac));
		}

		return evmUnitDataList;
	}

	private static ArrayList<Ticket> importTicketCsv(File file) throws IOException, ParseException {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();

		// ファイルを読み込む
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		// 読み込んだファイルを１行ずつ処理する
		String[] columTitleArray = null;
		String line;
		StringTokenizer token;
		// 1行目タイトル
		if ((line = br.readLine()) != null) {
			token = new StringTokenizer(line, ",");
			columTitleArray = new String[token.countTokens()];
			int i = 0;
			while (token.hasMoreTokens()) {
				columTitleArray[i] = token.nextToken();
				i++;
			}
		}
		while ((line = br.readLine()) != null) {
			// 区切り文字","で分割する
			token = new StringTokenizer(line, ",");

			// Ticketインスタンス生成
			Ticket ticket = new Ticket();
			ticketList.add(ticket);
			for (String columTitle : columTitleArray) {
				String str = token.nextToken();
				ticket.putData(columTitle, str);
			}
		}

		// 終了処理
		br.close();

		return ticketList;
	}

	private static ArrayList<TimeLog> importTimeLogCsv(File file) throws IOException, ParseException {
		ArrayList<TimeLog> timeLogList = new ArrayList<TimeLog>();

		// ファイルを読み込む
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		// 読み込んだファイルを１行ずつ処理する
		String[] columTitleArray = null;
		String line;
		StringTokenizer token;
		// 1行目タイトル
		if ((line = br.readLine()) != null) {
			token = new StringTokenizer(line, ",");
			columTitleArray = new String[token.countTokens()];
			int i = 0;
			while (token.hasMoreTokens()) {
				columTitleArray[i] = token.nextToken();
				i++;
			}
		}
		while ((line = br.readLine()) != null) {
			// 区切り文字","で分割する
			token = new StringTokenizer(line, ",");

			// Ticketインスタンス生成
			TimeLog log = new TimeLog();
			timeLogList.add(log);
			for (String columTitle : columTitleArray) {
				String str = token.nextToken();
				log.putData(columTitle, str);
			}
		}

		// 終了処理
		br.close();
		
		System.out.println(timeLogList.get(0).getDate());

		return timeLogList;
	}

}
