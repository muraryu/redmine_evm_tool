package src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

	private Integer ticketNumber; // #
	private String tracker; // トラッカー
	private String status; // ステータス
	private String title; // 題名
	private String userAssigned; // 担当者
	private Double estimatedTime; // 予定工数
	private Double spentTime; // 作業時間
	private Date dueDate; // 期日
	private String project; // プロジェクト
	private Integer parentTicketNumber; // 親チケット
	private String priority; // 優先度
	private String userCreated; // 作成者
	private Date updateTime; // 更新日
	private String category; // カテゴリ
	private String targetVersion; // 対象バージョン
	private Date startDate; // 開始日
	private Double sumEstimatedTime; // 合計予定工数
	private Double sumSpentTime; // 合計作業時間
	private Integer done; // 進捗率
	private Date addDate; // 作成日
	private Date closeDate; // 終了日
	private Integer relatedTicket; // 関連するチケット
	private String privateFlag; // プライベート
	
	public Integer getTicketNumber() {
		return ticketNumber;
	}

	public String getTracker() {
		return tracker;
	}

	public String getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public String getUserAssigned() {
		return userAssigned;
	}

	public Double getEstimatedTime() {
		return estimatedTime;
	}

	public Double getSpentTime() {
		return spentTime;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public String getProject() {
		return project;
	}

	public Integer getParentTicketNumber() {
		return parentTicketNumber;
	}

	public String getPriority() {
		return priority;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getCategory() {
		return category;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Double getSumEstimatedTime() {
		return sumEstimatedTime;
	}

	public Double getSumSpentTime() {
		return sumSpentTime;
	}

	public Integer getDone() {
		return done;
	}

	public Date getAddDate() {
		return addDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public Integer getRelatedTicket() {
		return relatedTicket;
	}

	public String getPrivateFlag() {
		return privateFlag;
	}

	// Stringでセット
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = Integer.parseInt(ticketNumber);
	}

	public void putData(String columTitle, String data) throws ParseException {

		// 空の場合は格納しない
		if (data.equals("\"\"")) {
			return;
		}

		SimpleDateFormat sdf;
		switch (columTitle) {
		case "#":
			this.ticketNumber = Integer.parseInt(data);
			break;
		case "トラッカー":
			this.tracker = data;
			break;
		case "ステータス":
			this.status = data;
			break;
		case "題名":
			this.title = data;
			break;
		case "担当者":
			this.userAssigned = data;
			break;
		case "予定工数":
			this.estimatedTime = Double.parseDouble(data);
			break;
		case "作業時間":
			this.spentTime = Double.parseDouble(data);
			break;
		case "期日":
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			this.dueDate = sdf.parse(data);
			break;
		case "プロジェクト":
			this.project = data;
			break;
		case "親チケット":
			this.parentTicketNumber = Integer.parseInt(data);
			break;
		case "優先度":
			this.priority = data;
			break;
		case "作成者":
			this.userCreated = data;
			break;
		case "更新日":
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			this.updateTime = sdf.parse(data);
			break;
		case "カテゴリ":
			this.category = data;
			break;
		case "対象バージョン":
			this.targetVersion = data;
			break;
		case "開始日":
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			this.startDate = sdf.parse(data);
			break;
		case "合計予定工数":
			this.sumEstimatedTime = Double.parseDouble(data);
			break;
		case "合計作業時間":
			this.sumSpentTime = Double.parseDouble(data);
			break;
		case "進捗率":
			this.done = Integer.parseInt(data);
			break;
		case "作成日":
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			this.addDate = sdf.parse(data);
			break;
		case "終了日":
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			this.closeDate = sdf.parse(data);
			break;
		case "関連するチケット":
			this.relatedTicket = Integer.parseInt(data);
			break;
		case "プライベート":
			this.privateFlag = data;
			break;
		}
	}

}
