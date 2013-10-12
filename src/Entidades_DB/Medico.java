/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m"),
    @NamedQuery(name = "Medico.findByCedula", query = "SELECT m FROM Medico m WHERE m.cedula = :cedula"),
    @NamedQuery(name = "Medico.findByNombre", query = "SELECT m FROM Medico m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Medico.findByEspecialidad", query = "SELECT m FROM Medico m WHERE m.especialidad = :especialidad"),
    @NamedQuery(name = "Medico.findBySubespecialidad", query = "SELECT m FROM Medico m WHERE m.subespecialidad = :subespecialidad"),
    @NamedQuery(name = "Medico.findByLicencia", query = "SELECT m FROM Medico m WHERE m.licencia = :licencia"),
    @NamedQuery(name = "Medico.findByDireccionConsultorio", query = "SELECT m FROM Medico m WHERE m.direccionConsultorio = :direccionConsultorio"),
    @NamedQuery(name = "Medico.findByIdIps", query = "SELECT m FROM Medico m WHERE m.idIps = :idIps")})
public class Medico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Cedula")
    private String cedula;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Especialidad")
    private String especialidad;
    @Column(name = "Subespecialidad")
    private String subespecialidad;
    @Column(name = "Licencia")
    private String licencia;
    @Column(name = "Direccion_Consultorio")
    private String direccionConsultorio;
    @Column(name = "ID_IPS")
    private String idIps;
    @OneToMany(mappedBy = "iDMedico")
    private List<CitasGenerales> citasGeneralesList;
    @OneToMany(mappedBy = "iDMedico")
    private List<Urgencias> urgenciasList;
    @OneToMany(mappedBy = "iDMedicoRemite")
    private List<Remisiones> remisionesList;
    @OneToMany(mappedBy = "iDMedico")
    private List<Remisiones> remisionesList1;
    @OneToMany(mappedBy = "iDMedico")
    private List<FormulasMedicas> formulasMedicasList;
    @OneToMany(mappedBy = "iDMedico")
    private List<Hospitalizaciones> hospitalizacionesList;

    public Medico() {
    }

    public Medico(String cedula) {
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

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getDireccionConsultorio() {
        return direccionConsultorio;
    }

    public void setDireccionConsultorio(String direccionConsultorio) {
        this.direccionConsultorio = direccionConsultorio;
    }

    public String getIdIps() {
        return idIps;
    }

    public void setIdIps(String idIps) {
        this.idIps = idIps;
    }

    @XmlTransient
    public List<CitasGenerales> getCitasGeneralesList() {
        return citasGeneralesList;
    }

    public void setCitasGeneralesList(List<CitasGenerales> citasGeneralesList) {
        this.citasGeneralesList = citasGeneralesList;
    }

    @XmlTransient
    public List<Urgencias> getUrgenciasList() {
        return urgenciasList;
    }

    public void setUrgenciasList(List<Urgencias> urgenciasList) {
        this.urgenciasList = urgenciasList;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList1() {
        return remisionesList1;
    }

    public void setRemisionesList1(List<Remisiones> remisionesList1) {
        this.remisionesList1 = remisionesList1;
    }

    @XmlTransient
    public List<FormulasMedicas> getFormulasMedicasList() {
        return formulasMedicasList;
    }

    public void setFormulasMedicasList(List<FormulasMedicas> formulasMedicasList) {
        this.formulasMedicasList = formulasMedicasList;
    }

    @XmlTransient
    public List<Hospitalizaciones> getHospitalizacionesList() {
        return hospitalizacionesList;
    }

    public void setHospitalizacionesList(List<Hospitalizaciones> hospitalizacionesList) {
        this.hospitalizacionesList = hospitalizacionesList;
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
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Medico[ cedula=" + cedula + " ]";
    }
    
}
