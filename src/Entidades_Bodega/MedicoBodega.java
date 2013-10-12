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
@Table(name = "medico_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicoBodega.findAll", query = "SELECT m FROM MedicoBodega m"),
    @NamedQuery(name = "MedicoBodega.findByMedicoKey", query = "SELECT m FROM MedicoBodega m WHERE m.medicoKey = :medicoKey"),
    @NamedQuery(name = "MedicoBodega.findByIdMedico", query = "SELECT m FROM MedicoBodega m WHERE m.idMedico = :idMedico"),
    @NamedQuery(name = "MedicoBodega.findByNombre", query = "SELECT m FROM MedicoBodega m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MedicoBodega.findByEspecialidad", query = "SELECT m FROM MedicoBodega m WHERE m.especialidad = :especialidad"),
    @NamedQuery(name = "MedicoBodega.findBySubespecialidad", query = "SELECT m FROM MedicoBodega m WHERE m.subespecialidad = :subespecialidad"),
    @NamedQuery(name = "MedicoBodega.findByDireccion", query = "SELECT m FROM MedicoBodega m WHERE m.direccion = :direccion"),
    @NamedQuery(name = "MedicoBodega.findByIpsNombre", query = "SELECT m FROM MedicoBodega m WHERE m.ipsNombre = :ipsNombre"),
    @NamedQuery(name = "MedicoBodega.findByIpsId", query = "SELECT m FROM MedicoBodega m WHERE m.ipsId = :ipsId")})
public class MedicoBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "medico_key")
    private Integer medicoKey;
    @Column(name = "id_medico")
    private String idMedico;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "subespecialidad")
    private String subespecialidad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ips_nombre")
    private String ipsNombre;
    @Column(name = "ips_id")
    private String ipsId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoBodega")
    private List<FormulasBodega> formulasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public MedicoBodega() {
    }

    public MedicoBodega(Integer medicoKey) {
        this.medicoKey = medicoKey;
    }

    public Integer getMedicoKey() {
        return medicoKey;
    }

    public void setMedicoKey(Integer medicoKey) {
        this.medicoKey = medicoKey;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getSubespecialidad() {
        return subespecialidad;
    }

    public void setSubespecialidad(String subespecialidad) {
        this.subespecialidad = subespecialidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIpsNombre() {
        return ipsNombre;
    }

    public void setIpsNombre(String ipsNombre) {
        this.ipsNombre = ipsNombre;
    }

    public String getIpsId() {
        return ipsId;
    }

    public void setIpsId(String ipsId) {
        this.ipsId = ipsId;
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
    public List<RemisionesBodega> getRemisionesBodegaList() {
        return remisionesBodegaList;
    }

    public void setRemisionesBodegaList(List<RemisionesBodega> remisionesBodegaList) {
        this.remisionesBodegaList = remisionesBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicoKey != null ? medicoKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicoBodega)) {
            return false;
        }
        MedicoBodega other = (MedicoBodega) object;
        if ((this.medicoKey == null && other.medicoKey != null) || (this.medicoKey != null && !this.medicoKey.equals(other.medicoKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.MedicoBodega[ medicoKey=" + medicoKey + " ]";
    }
    
}
