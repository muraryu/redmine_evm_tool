package evm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLog {

	private String project;		// �v���W�F�N�g
	private Date date;			// ���t
	private Integer week;		// Week
	private String user;		// ���[�U�[
	private String activity;	// ����
	private String title;		// �`�P�b�g
	private String comment;		// �R�����g
	private Double spentTime;	// ����

	public void putData(String columTitle, String data) throws ParseException {

		// do nothing if no data.
		if (data.equals("\"\"")) {
			return;
		}

		switch (columTitle) {
		case "�v���W�F�N�g" :
			this.project = data;
			break;
		case "���t":
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			this.date = sdf.parse(data);
			break;
		case "Week":
			this.week = Integer.parseInt(data);
			break;
		case "���[�U�[":
			this.user = data;
			break;
		case "����":
			this.activity = data;
			break;
		case "�`�P�b�g":
			this.title = data;
			break;
		case "�R�����g":
			this.comment = data;
			break;
		case "����":
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
