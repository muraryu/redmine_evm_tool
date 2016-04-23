package src;

import java.util.Date;

/**
 * 時系列EVMデータの一時点を示すデータ
 * 
 * @author ryuji
 *
 */
public class EvmUnitData {

	private Date date;
	
	private double pv = 0; // アーンド・バリュー（出来高） (EV, Earned Value)
	private double ev = 0; // プランド・バリュー（計画価値） (PV, Planned Value)
	private double ac = 0; // 実コスト (AC, Actual Cost)
	private double bac = 0; // 完成時総予算 (BAC, Budget at Completion)
	private double cv = 0; // コスト差異 (CV, Cost Variance)
	private double sv = 0; // スケジュール差異 (SV, Schedule Variance)
	private double cpi = 0; // コスト効率指数 (CPI, Cost Performance Index)
	private double spi = 0; // スケジュール効率指数(SPI, Schedule Performance Index)
	private double eac = 0; // 完成時総コスト見積り (EAC, Estimate At Completion)
	private double etc = 0; // 残作業のコスト見積り (ETC, Estimate To Completion)

	public EvmUnitData(Date date, double pv, double ev, double ac, double bac) {
		this.date = date;
		this.pv = pv;
		this.ev = ev;
		this.ac = ac;
		this.bac = bac;
		this.cv = ev - ac;
		this.sv = ev - pv;
		if (ac != 0) {
			this.cpi = ev / ac;
		} else {
			this.cpi = 1;
		}
		if (pv != 0) {
			this.spi = ev / pv;
		} else {
			this.spi = 1;
		}
		if (this.cpi != 0) {
			this.eac = ac + (bac - ev) / this.cpi;
		} else {
			this.eac = bac;
		}
		this.etc = this.eac - ac;
	}

	public Date getDate() {
		return date;
	}

	public double getPv() {
		return pv;
	}

	public double getEv() {
		return ev;
	}

	public double getAc() {
		return ac;
	}

	public double getBac() {
		return bac;
	}

	public double getCv() {
		return cv;
	}

	public double getSv() {
		return sv;
	}

	public double getCpi() {
		return cpi;
	}

	public double getSpi() {
		return spi;
	}

	public double getEac() {
		return eac;
	}

	public double getEtc() {
		return etc;
	}
	
	

}
