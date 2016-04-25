package redmine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

	private Integer ticketNumber; // #
	private String tracker; // �g���b�J�[
	private String status; // �X�e�[�^�X
	private String title; // �薼
	private String userAssigned; // �S����
	private Double estimatedTime; // �\��H��
	private Double spentTime; // ��Ǝ���
	private Date dueDate; // ����
	private String project; // �v���W�F�N�g
	private Integer parentTicketNumber; // �e�`�P�b�g
	private String priority; // �D��x
	private String userCreated; // �쐬��
	private Date updateTime; // �X�V��
	private String category; // �J�e�S��
	private String targetVersion; // �Ώۃo�[�W����
	private Date startDate; // �J�n��
	private Double sumEstimatedTime; // ���v�\��H��
	private Double sumSpentTime; // ���v��Ǝ���
	private Integer done; // �i����
	private Date addDate; // �쐬��
	private Date closeDate; // �I����
	private Integer relatedTicket; // �֘A����`�P�b�g
	private String privateFlag; // �v���C�x�[�g

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

	// String�ŃZ�b�g
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = Integer.parseInt(ticketNumber);
	}

	public void putData(String columTitle, String data) throws ParseException {

		// ��̏ꍇ�͊i�[���Ȃ�
		if (data.equals("\"\"")) {
			return;
		}

		SimpleDateFormat sdf;
		switch (columTitle) {
		case "#":
			this.ticketNumber = Integer.parseInt(data);
			break;
		case "�g���b�J�[":
			this.tracker = data;
			break;
		case "�X�e�[�^�X":
			this.status = data;
			break;
		case "�薼":
			this.title = data;
			break;
		case "�S����":
			this.userAssigned = data;
			break;
		case "�\��H��":
			this.estimatedTime = Double.parseDouble(data);
			break;
		case "��Ǝ���":
			this.spentTime = Double.parseDouble(data);
			break;
		case "����":
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			this.dueDate = sdf.parse(data);
			break;
		case "�v���W�F�N�g":
			this.project = data;
			break;
		case "�e�`�P�b�g":
			this.parentTicketNumber = Integer.parseInt(data);
			break;
		case "�D��x":
			this.priority = data;
			break;
		case "�쐬��":
			this.userCreated = data;
			break;
		case "�X�V��":
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			this.updateTime = sdf.parse(data);
			break;
		case "�J�e�S��":
			this.category = data;
			break;
		case "�Ώۃo�[�W����":
			this.targetVersion = data;
			break;
		case "�J�n��":
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			this.startDate = sdf.parse(data);
			break;
		case "���v�\��H��":
			this.sumEstimatedTime = Double.parseDouble(data);
			break;
		case "���v��Ǝ���":
			this.sumSpentTime = Double.parseDouble(data);
			break;
		case "�i����":
			this.done = Integer.parseInt(data);
			break;
		case "�쐬��":
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			this.addDate = sdf.parse(data);
			break;
		case "�I����":
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			this.closeDate = sdf.parse(data);
			break;
		case "�֘A����`�P�b�g":
			this.relatedTicket = Integer.parseInt(data);
			break;
		case "�v���C�x�[�g":
			this.privateFlag = data;
			break;
		}
	}

}
