/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "afiliaciones_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AfiliacionesBodega.findAll", query = "SELECT a FROM AfiliacionesBodega a"),
    @NamedQuery(name = "AfiliacionesBodega.findByPacienteKey", query = "SELECT a FROM AfiliacionesBodega a WHERE a.afiliacionesBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "AfiliacionesBodega.findByDemografiaPacienteKey", query = "SELECT a FROM AfiliacionesBodega a WHERE a.afiliacionesBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "AfiliacionesBodega.findByFechaKey", query = "SELECT a FROM AfiliacionesBodega a WHERE a.afiliacionesBodegaPK.fechaKey = :fechaKey"),
    @NamedQuery(name = "AfiliacionesBodega.findByEmpresaKey", query = "SELECT a FROM AfiliacionesBodega a WHERE a.afiliacionesBodegaPK.empresaKey = :empresaKey"),
    @NamedQuery(name = "AfiliacionesBodega.findByIpsKey", query = "SELECT a FROM AfiliacionesBodega a WHERE a.afiliacionesBodegaPK.ipsKey = :ipsKey"),
    @NamedQuery(name = "AfiliacionesBodega.findByCostoAdicional", query = "SELECT a FROM AfiliacionesBodega a WHERE a.costoAdicional = :costoAdicional")})
public class AfiliacionesBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AfiliacionesBodegaPK afiliacionesBodegaPK;
    @Column(name = "costo_adicional")
    private Integer costoAdicional;
    @JoinColumn(name = "ips_key", referencedColumnName = "ips_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IpsBodega ipsBodega;
    @JoinColumn(name = "empresa_key", referencedColumnName = "empresa_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EmpresaBodega empresaBodega;
    @JoinColumn(name = "fecha_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;

    public AfiliacionesBodega() {
    }

    public AfiliacionesBodega(AfiliacionesBodegaPK afiliacionesBodegaPK) {
        this.afiliacionesBodegaPK = afiliacionesBodegaPK;
    }

    public AfiliacionesBodega(int pacienteKey, int demografiaPacienteKey, long fechaKey, int empresaKey, int ipsKey) {
        this.afiliacionesBodegaPK = new AfiliacionesBodegaPK(pacienteKey, demografiaPacienteKey, fechaKey, empresaKey, ipsKey);
    }

    public AfiliacionesBodegaPK getAfiliacionesBodegaPK() {
        return afiliacionesBodegaPK;
    }

    public void setAfiliacionesBodegaPK(AfiliacionesBodegaPK afiliacionesBodegaPK) {
        this.afiliacionesBodegaPK = afiliacionesBodegaPK;
    }

    public Integer getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(Integer costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public IpsBodega getIpsBodega() {
        return ipsBodega;
    }

    public void setIpsBodega(IpsBodega ipsBodega) {
        this.ipsBodega = ipsBodega;
    }

    public EmpresaBodega getEmpresaBodega() {
        return empresaBodega;
    }

    public void setEmpresaBodega(EmpresaBodega empresaBodega) {
        this.empresaBodega = empresaBodega;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public DemografiaPacienteBodega getDemografiaPacienteBodega() {
        return demografiaPacienteBodega;
    }

    public void setDemografiaPacienteBodega(DemografiaPacienteBodega demografiaPacienteBodega) {
        this.demografiaPacienteBodega = demografiaPacienteBodega;
    }

    public PacienteBodega getPacienteBodega() {
        return pacienteBodega;
    }

    public void setPacienteBodega(PacienteBodega pacienteBodega) {
        this.pacienteBodega = pacienteBodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afiliacionesBodegaPK != null ? afiliacionesBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionesBodega)) {
            return false;
        }
        AfiliacionesBodega other = (AfiliacionesBodega) object;
        if ((this.afiliacionesBodegaPK == null && other.afiliacionesBodegaPK != null) || (this.afiliacionesBodegaPK != null && !this.afiliacionesBodegaPK.equals(other.afiliacionesBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.AfiliacionesBodega[ afiliacionesBodegaPK=" + afiliacionesBodegaPK + " ]";
    }
    
}
