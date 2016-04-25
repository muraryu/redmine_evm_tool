package evm;

import java.util.Date;

/**
 * one data of time series EVM data.
 *
 * @author ryuji
 *
 */
public class EvmUnitData {

	private Date date;

	private double pv = 0; // EV, Earned Value
	private double ev = 0; // PV, Planned Value
	private double ac = 0; // AC, Actual Cost
	private double bac = 0; // BAC, Budget at Completion
	private double cv = 0; // CV, Cost Variance
	private double sv = 0; // SV, Schedule Variance
	private double cpi = 0; // CPI, Cost Performance Index
	private double spi = 0; // SPI, Schedule Performance Index
	private double eac = 0; // EAC, Estimate At Completion
	private double etc = 0; // ETC, Estimate To Completion

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
