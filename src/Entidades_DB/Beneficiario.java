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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Beneficiario.findAll", query = "SELECT b FROM Beneficiario b"),
    @NamedQuery(name = "Beneficiario.findByTipoIdentificacion", query = "SELECT b FROM Beneficiario b WHERE b.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "Beneficiario.findByIDBeneficiario", query = "SELECT b FROM Beneficiario b WHERE b.iDBeneficiario = :iDBeneficiario"),
    @NamedQuery(name = "Beneficiario.findByParentesco", query = "SELECT b FROM Beneficiario b WHERE b.parentesco = :parentesco"),
    @NamedQuery(name = "Beneficiario.findByNombre", query = "SELECT b FROM Beneficiario b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Beneficiario.findByFechaNacimiento", query = "SELECT b FROM Beneficiario b WHERE b.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Beneficiario.findBySexo", query = "SELECT b FROM Beneficiario b WHERE b.sexo = :sexo"),
    @NamedQuery(name = "Beneficiario.findByEstadoCivil", query = "SELECT b FROM Beneficiario b WHERE b.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Beneficiario.findByTipoDiscapacidad", query = "SELECT b FROM Beneficiario b WHERE b.tipoDiscapacidad = :tipoDiscapacidad")})
public class Beneficiario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "Tipo_Identificacion")
    private String tipoIdentificacion;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Beneficiario")
    private String iDBeneficiario;
    @Column(name = "Parentesco")
    private String parentesco;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "Sexo")
    private String sexo;
    @Column(name = "Estado_Civil")
    private String estadoCivil;
    @Column(name = "Tipo_Discapacidad")
    private String tipoDiscapacidad;
    @ManyToMany(mappedBy = "beneficiarioList")
    private List<Cotizante> cotizanteList;

    public Beneficiario() {
    }

    public Beneficiario(String iDBeneficiario) {
        this.iDBeneficiario = iDBeneficiario;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIDBeneficiario() {
        return iDBeneficiario;
    }

    public void setIDBeneficiario(String iDBeneficiario) {
        this.iDBeneficiario = iDBeneficiario;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    @XmlTransient
    public List<Cotizante> getCotizanteList() {
        return cotizanteList;
    }

    public void setCotizanteList(List<Cotizante> cotizanteList) {
        this.cotizanteList = cotizanteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDBeneficiario != null ? iDBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficiario)) {
            return false;
        }
        Beneficiario other = (Beneficiario) object;
        if ((this.iDBeneficiario == null && other.iDBeneficiario != null) || (this.iDBeneficiario != null && !this.iDBeneficiario.equals(other.iDBeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Beneficiario[ iDBeneficiario=" + iDBeneficiario + " ]";
    }
    
}
