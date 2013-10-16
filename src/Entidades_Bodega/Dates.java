/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "dates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dates.findAll", query = "SELECT d FROM Dates d"),
    @NamedQuery(name = "Dates.findByDateId", query = "SELECT d FROM Dates d WHERE d.dateId = :dateId"),
    @NamedQuery(name = "Dates.findByDate", query = "SELECT d FROM Dates d WHERE d.date = :date"),
    @NamedQuery(name = "Dates.findByTimestamp", query = "SELECT d FROM Dates d WHERE d.timestamp = :timestamp"),
    @NamedQuery(name = "Dates.findByHoliday", query = "SELECT d FROM Dates d WHERE d.holiday = :holiday"),
    @NamedQuery(name = "Dates.findByNextBusinessDay", query = "SELECT d FROM Dates d WHERE d.nextBusinessDay = :nextBusinessDay"),
    @NamedQuery(name = "Dates.findByWeekend", query = "SELECT d FROM Dates d WHERE d.weekend = :weekend"),
    @NamedQuery(name = "Dates.findByDayOfWeek", query = "SELECT d FROM Dates d WHERE d.dayOfWeek = :dayOfWeek"),
    @NamedQuery(name = "Dates.findByMonth", query = "SELECT d FROM Dates d WHERE d.month = :month"),
    @NamedQuery(name = "Dates.findByMonthDay", query = "SELECT d FROM Dates d WHERE d.monthDay = :monthDay"),
    @NamedQuery(name = "Dates.findByYear", query = "SELECT d FROM Dates d WHERE d.year = :year"),
    @NamedQuery(name = "Dates.findByWeekStartingMonday", query = "SELECT d FROM Dates d WHERE d.weekStartingMonday = :weekStartingMonday")})
public class Dates implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<AfiliacionesBodega> afiliacionesBodegaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "date_id")
    private Long dateId;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "timestamp")
    private long timestamp;
    @Basic(optional = false)
    @Column(name = "holiday")
    private String holiday;
    @Column(name = "next_business_day")
    @Temporal(TemporalType.DATE)
    private Date nextBusinessDay;
    @Basic(optional = false)
    @Column(name = "weekend")
    private String weekend;
    @Basic(optional = false)
    @Column(name = "day_of_week")
    private String dayOfWeek;
    @Basic(optional = false)
    @Column(name = "month")
    private String month;
    @Basic(optional = false)
    @Column(name = "month_day")
    private int monthDay;
    @Basic(optional = false)
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @Column(name = "week_starting_monday")
    private String weekStartingMonday;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<FormulasBodega> formulasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<PagosBodega> pagosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<RetirosBodega> retirosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dates")
    private List<RemisionesBodega> remisionesBodegaList;

    public Dates() {
    }

    public Dates(Long dateId) {
        this.dateId = dateId;
    }

    public Dates(Long dateId, Date date, long timestamp, String holiday, String weekend, String dayOfWeek, String month, int monthDay, int year, String weekStartingMonday) {
        this.dateId = dateId;
        this.date = date;
        this.timestamp = timestamp;
        this.holiday = holiday;
        this.weekend = weekend;
        this.dayOfWeek = dayOfWeek;
        this.month = month;
        this.monthDay = monthDay;
        this.year = year;
        this.weekStartingMonday = weekStartingMonday;
    }

    public Long getDateId() {
        return dateId;
    }

    public void setDateId(Long dateId) {
        this.dateId = dateId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Date getNextBusinessDay() {
        return nextBusinessDay;
    }

    public void setNextBusinessDay(Date nextBusinessDay) {
        this.nextBusinessDay = nextBusinessDay;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(int monthDay) {
        this.monthDay = monthDay;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWeekStartingMonday() {
        return weekStartingMonday;
    }

    public void setWeekStartingMonday(String weekStartingMonday) {
        this.weekStartingMonday = weekStartingMonday;
    }

    @XmlTransient
    public List<CitasBodega> getCitasBodegaList() {
        return citasBodegaList;
    }

    public void setCitasBodegaList(List<CitasBodega> citasBodegaList) {
        this.citasBodegaList = citasBodegaList;
    }

    @XmlTransient
    public List<FormulasBodega> getFormulasBodegaList() {
        return formulasBodegaList;
    }

    public void setFormulasBodegaList(List<FormulasBodega> formulasBodegaList) {
        this.formulasBodegaList = formulasBodegaList;
    }

    @XmlTransient
    public List<PagosBodega> getPagosBodegaList() {
        return pagosBodegaList;
    }

    public void setPagosBodegaList(List<PagosBodega> pagosBodegaList) {
        this.pagosBodegaList = pagosBodegaList;
    }

    @XmlTransient
    public List<RetirosBodega> getRetirosBodegaList() {
        return retirosBodegaList;
    }

    public void setRetirosBodegaList(List<RetirosBodega> retirosBodegaList) {
        this.retirosBodegaList = retirosBodegaList;
    }

    @XmlTransient
    public List<UrgenciasBodega> getUrgenciasBodegaList() {
        return urgenciasBodegaList;
    }

    public void setUrgenciasBodegaList(List<UrgenciasBodega> urgenciasBodegaList) {
        this.urgenciasBodegaList = urgenciasBodegaList;
    }

    @XmlTransient
    public List<RemisionesBodega> getRemisionesBodegaList() {
        return remisionesBodegaList;
    }

    public void setRemisionesBodegaList(List<RemisionesBodega> remisionesBodegaList) {
        this.remisionesBodegaList = remisionesBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateId != null ? dateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dates)) {
            return false;
        }
        Dates other = (Dates) object;
        if ((this.dateId == null && other.dateId != null) || (this.dateId != null && !this.dateId.equals(other.dateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.Dates[ dateId=" + dateId + " ]";
    }

    @XmlTransient
    public List<AfiliacionesBodega> getAfiliacionesBodegaList() {
        return afiliacionesBodegaList;
    }

    public void setAfiliacionesBodegaList(List<AfiliacionesBodega> afiliacionesBodegaList) {
        this.afiliacionesBodegaList = afiliacionesBodegaList;
    }
}
