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
@Table(name = "paciente_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PacienteBodega.findAll", query = "SELECT p FROM PacienteBodega p"),
    @NamedQuery(name = "PacienteBodega.findByPacienteKey", query = "SELECT p FROM PacienteBodega p WHERE p.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "PacienteBodega.findByIdPaciente", query = "SELECT p FROM PacienteBodega p WHERE p.idPaciente = :idPaciente"),
    @NamedQuery(name = "PacienteBodega.findByTipoDocumento", query = "SELECT p FROM PacienteBodega p WHERE p.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "PacienteBodega.findByTipoPaciente", query = "SELECT p FROM PacienteBodega p WHERE p.tipoPaciente = :tipoPaciente"),
    @NamedQuery(name = "PacienteBodega.findByIdCotizante", query = "SELECT p FROM PacienteBodega p WHERE p.idCotizante = :idCotizante"),
    @NamedQuery(name = "PacienteBodega.findByNombre", query = "SELECT p FROM PacienteBodega p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PacienteBodega.findBySexo", query = "SELECT p FROM PacienteBodega p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "PacienteBodega.findByFechaNacimiento", query = "SELECT p FROM PacienteBodega p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PacienteBodega.findByFechaAfiliacion", query = "SELECT p FROM PacienteBodega p WHERE p.fechaAfiliacion = :fechaAfiliacion"),
    @NamedQuery(name = "PacienteBodega.findByDiscapacidad", query = "SELECT p FROM PacienteBodega p WHERE p.discapacidad = :discapacidad")})
public class PacienteBodega implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<AfiliacionesBodega> afiliacionesBodegaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "paciente_key")
    private Integer pacienteKey;
    @Column(name = "id_paciente")
    private String idPaciente;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(name = "tipo_paciente")
    private String tipoPaciente;
    @Column(name = "id_cotizante")
    private String idCotizante;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "fecha_afiliacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAfiliacion;
    @Column(name = "Discapacidad")
    private String discapacidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<CitasBodega> citasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<FormulasBodega> formulasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<PagosBodega> pagosBodegaList;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<RetirosBodega> retirosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<UrgenciasBodega> urgenciasBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteBodega")
    private List<RemisionesBodega> remisionesBodegaList;

    public PacienteBodega() {
    }

    public PacienteBodega(Integer pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public Integer getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(Integer pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public String getIdCotizante() {
        return idCotizante;
    }

    public void setIdCotizante(String idCotizante) {
        this.idCotizante = idCotizante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
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
        hash += (pacienteKey != null ? pacienteKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacienteBodega)) {
            return false;
        }
        PacienteBodega other = (PacienteBodega) object;
        if ((this.pacienteKey == null && other.pacienteKey != null) || (this.pacienteKey != null && !this.pacienteKey.equals(other.pacienteKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.PacienteBodega[ pacienteKey=" + pacienteKey + " ]";
    }

    @XmlTransient
    public List<AfiliacionesBodega> getAfiliacionesBodegaList() {
        return afiliacionesBodegaList;
    }

    public void setAfiliacionesBodegaList(List<AfiliacionesBodega> afiliacionesBodegaList) {
        this.afiliacionesBodegaList = afiliacionesBodegaList;
    }
    
}
