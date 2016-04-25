package evm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLog {

	private String project;		// プロジェクト
	private Date date;			// 日付
	private Integer week;		// Week
	private String user;		// ユーザー
	private String activity;	// 活動
	private String title;		// チケット
	private String comment;		// コメント
	private Double spentTime;	// 時間

	public void putData(String columTitle, String data) throws ParseException {

		// do nothing if no data.
		if (data.equals("\"\"")) {
			return;
		}

		switch (columTitle) {
		case "プロジェクト" :
			this.project = data;
			break;
		case "日付":
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			this.date = sdf.parse(data);
			break;
		case "Week":
			this.week = Integer.parseInt(data);
			break;
		case "ユーザー":
			this.user = data;
			break;
		case "活動":
			this.activity = data;
			break;
		case "チケット":
			this.title = data;
			break;
		case "コメント":
			this.comment = data;
			break;
		case "時間":
			this.spentTime = Double.parseDouble(data);
			break;
		}

	}

	public String getProject() {
		return project;
	}

	public Date getDate() {
		return date;
	}

	public Integer getWeek() {
		return week;
	}

	public String getUser() {
		return user;
	}

	public String getActivity() {
		return activity;
	}

	public String getTitle() {
		return title;
	}

	public String getComment() {
		return comment;
	}

	public Double getSpentTime() {
		return spentTime;
	}

}
