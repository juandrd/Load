/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "cotizante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizante.findAll", query = "SELECT c FROM Cotizante c"),
    @NamedQuery(name = "Cotizante.findByCedula", query = "SELECT c FROM Cotizante c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Cotizante.findByNombre", query = "SELECT c FROM Cotizante c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cotizante.findByTipoCotizante", query = "SELECT c FROM Cotizante c WHERE c.tipoCotizante = :tipoCotizante"),
    @NamedQuery(name = "Cotizante.findByDireccion", query = "SELECT c FROM Cotizante c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cotizante.findByEstadoCivil", query = "SELECT c FROM Cotizante c WHERE c.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Cotizante.findBySexo", query = "SELECT c FROM Cotizante c WHERE c.sexo = :sexo"),
    @NamedQuery(name = "Cotizante.findByFechaNacimiento", query = "SELECT c FROM Cotizante c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Cotizante.findByNivelEscolaridad", query = "SELECT c FROM Cotizante c WHERE c.nivelEscolaridad = :nivelEscolaridad"),
    @NamedQuery(name = "Cotizante.findByEstracto", query = "SELECT c FROM Cotizante c WHERE c.estracto = :estracto"),
    @NamedQuery(name = "Cotizante.findByProvieneOtraEPS", query = "SELECT c FROM Cotizante c WHERE c.provieneOtraEPS = :provieneOtraEPS"),
    @NamedQuery(name = "Cotizante.findBySalarioBase", query = "SELECT c FROM Cotizante c WHERE c.salarioBase = :salarioBase"),
    @NamedQuery(name = "Cotizante.findByFechaAfiliacion", query = "SELECT c FROM Cotizante c WHERE c.fechaAfiliacion = :fechaAfiliacion"),
    @NamedQuery(name = "Cotizante.findByTipoDiscapacidad", query = "SELECT c FROM Cotizante c WHERE c.tipoDiscapacidad = :tipoDiscapacidad")})
public class Cotizante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Cedula")
    private String cedula;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Tipo_Cotizante")
    private String tipoCotizante;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Estado_Civil")
    private String estadoCivil;
    @Column(name = "Sexo")
    private String sexo;
    @Column(name = "Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "Nivel_Escolaridad")
    private String nivelEscolaridad;
    @Column(name = "Estracto")
    private String estracto;
    @Column(name = "Proviene_Otra_EPS")
    private String provieneOtraEPS;
    @Column(name = "Salario_Base")
    private Integer salarioBase;
    @Column(name = "Fecha_Afiliacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAfiliacion;
    @Column(name = "Tipo_Discapacidad")
    private String tipoDiscapacidad;
    @JoinTable(name = "cotizante_beneficiario", joinColumns = {
        @JoinColumn(name = "Cotizante", referencedColumnName = "Cedula")}, inverseJoinColumns = {
        @JoinColumn(name = "Beneficiario", referencedColumnName = "ID_Beneficiario")})
    @ManyToMany
    private List<Beneficiario> beneficiarioList;
    @ManyToMany(mappedBy = "cotizanteList")
    private List<Empresa> empresaList;
    @JoinColumn(name = "ID_IPS", referencedColumnName = "ID_IPS")
    @ManyToOne
    private Ips idIps;
    @OneToMany(mappedBy = "iDUsuario")
    private List<Retiros> retirosList;
    @OneToMany(mappedBy = "iDUsuario")
    private List<Pagos> pagosList;

    public Cotizante() {
    }

    public Cotizante(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCotizante() {
        return tipoCotizante;
    }

    public void setTipoCotizante(String tipoCotizante) {
        this.tipoCotizante = tipoCotizante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNivelEscolaridad() {
        return nivelEscolaridad;
    }

    public void setNivelEscolaridad(String nivelEscolaridad) {
        this.nivelEscolaridad = nivelEscolaridad;
    }

    public String getEstracto() {
        return estracto;
    }

    public void setEstracto(String estracto) {
        this.estracto = estracto;
    }

    public String getProvieneOtraEPS() {
        return provieneOtraEPS;
    }

    public void setProvieneOtraEPS(String provieneOtraEPS) {
        this.provieneOtraEPS = provieneOtraEPS;
    }

    public Integer getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Integer salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    @XmlTransient
    public List<Beneficiario> getBeneficiarioList() {
        return beneficiarioList;
    }

    public void setBeneficiarioList(List<Beneficiario> beneficiarioList) {
        this.beneficiarioList = beneficiarioList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    public Ips getIdIps() {
        return idIps;
    }

    public void setIdIps(Ips idIps) {
        this.idIps = idIps;
    }

    @XmlTransient
    public List<Retiros> getRetirosList() {
        return retirosList;
    }

    public void setRetirosList(List<Retiros> retirosList) {
        this.retirosList = retirosList;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizante)) {
            return false;
        }
        Cotizante other = (Cotizante) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Cotizante[ cedula=" + cedula + " ]";
    }
    
}
