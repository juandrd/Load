/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "demografia_paciente_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemografiaPacienteBodega.findAll", query = "SELECT d FROM DemografiaPacienteBodega d"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByDemografiaPacienteKey", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByEstadoCivil", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByDireccion", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "DemografiaPacienteBodega.findBySalario", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.salario = :salario"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByNivelEscolaridad", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.nivelEscolaridad = :nivelEscolaridad"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByEstrato", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.estrato = :estrato"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByProvieneOtraEps", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.provieneOtraEps = :provieneOtraEps"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByIps", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.ips = :ips"),
    @NamedQuery(name = "DemografiaPacienteBodega.findByTipoCotizante", query = "SELECT d FROM DemografiaPacienteBodega d WHERE d.tipoCotizante = :tipoCotizante")})
public class DemografiaPacienteBodega implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<AfiliacionesBodega> afiliacionesBodegaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "demografia_paciente_key")
    private Integer demografiaPacienteKey;
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "salario")
    private Integer salario;
    @Column(name = "nivel_escolaridad")
    private String nivelEscolaridad;
    @Column(name = "estrato")
    private String estrato;
    @Column(name = "proviene_otra_eps")
    private String provieneOtraEps;
    @Column(name = "ips")
    private String ips;
    @Column(name = "tipo_cotizante")
    private String tipoCotizante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<FormulasBodega> formulasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<PagosBodega> pagosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<RetirosBodega> retirosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demografiaPacienteBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public DemografiaPacienteBodega() {
    }

    public DemografiaPacienteBodega(Integer demografiaPacienteKey) {
        this.demografiaPacienteKey = demografiaPacienteKey;
    }

    public Integer getDemografiaPacienteKey() {
        return demografiaPacienteKey;
    }

    public void setDemografiaPacienteKey(Integer demografiaPacienteKey) {
        this.demografiaPacienteKey = demografiaPacienteKey;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getNivelEscolaridad() {
        return nivelEscolaridad;
    }

    public void setNivelEscolaridad(String nivelEscolaridad) {
        this.nivelEscolaridad = nivelEscolaridad;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getProvieneOtraEps() {
        return provieneOtraEps;
    }

    public void setProvieneOtraEps(String provieneOtraEps) {
        this.provieneOtraEps = provieneOtraEps;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getTipoCotizante() {
        return tipoCotizante;
    }

    public void setTipoCotizante(String tipoCotizante) {
        this.tipoCotizante = tipoCotizante;
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
        hash += (demografiaPacienteKey != null ? demografiaPacienteKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemografiaPacienteBodega)) {
            return false;
        }
        DemografiaPacienteBodega other = (DemografiaPacienteBodega) object;
        if ((this.demografiaPacienteKey == null && other.demografiaPacienteKey != null) || (this.demografiaPacienteKey != null && !this.demografiaPacienteKey.equals(other.demografiaPacienteKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.DemografiaPacienteBodega[ demografiaPacienteKey=" + demografiaPacienteKey + " ]";
    }

    @XmlTransient
    public List<AfiliacionesBodega> getAfiliacionesBodegaList() {
        return afiliacionesBodegaList;
    }

    public void setAfiliacionesBodegaList(List<AfiliacionesBodega> afiliacionesBodegaList) {
        this.afiliacionesBodegaList = afiliacionesBodegaList;
    }
    
}
