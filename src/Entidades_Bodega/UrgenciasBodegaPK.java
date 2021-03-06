/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author USER
 */
@Embeddable
public class UrgenciasBodegaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "paciente_key")
    private int pacienteKey;
    @Basic(optional = false)
    @Column(name = "demografia_paciente_key")
    private int demografiaPacienteKey;
    @Basic(optional = false)
    @Column(name = "fecha_solicitud_key")
    private long fechaSolicitudKey;
    @Basic(optional = false)
    @Column(name = "fecha_atencion_key")
    private long fechaAtencionKey;
    @Basic(optional = false)
    @Column(name = "empresa_key")
    private int empresaKey;
    @Basic(optional = false)
    @Column(name = "preexistencia_key")
    private int preexistenciaKey;

    public UrgenciasBodegaPK() {
    }

    public UrgenciasBodegaPK(int pacienteKey, int demografiaPacienteKey, long fechaSolicitudKey, long fechaAtencionKey, int empresaKey, int preexistenciaKey) {
        this.pacienteKey = pacienteKey;
        this.demografiaPacienteKey = demografiaPacienteKey;
        this.fechaSolicitudKey = fechaSolicitudKey;
        this.fechaAtencionKey = fechaAtencionKey;
        this.empresaKey = empresaKey;
        this.preexistenciaKey = preexistenciaKey;
    }

    public int getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(int pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public int getDemografiaPacienteKey() {
        return demografiaPacienteKey;
    }

    public void setDemografiaPacienteKey(int demografiaPacienteKey) {
        this.demografiaPacienteKey = demografiaPacienteKey;
    }

    public long getFechaSolicitudKey() {
        return fechaSolicitudKey;
    }

    public void setFechaSolicitudKey(long fechaSolicitudKey) {
        this.fechaSolicitudKey = fechaSolicitudKey;
    }

    public long getFechaAtencionKey() {
        return fechaAtencionKey;
    }

    public void setFechaAtencionKey(long fechaAtencionKey) {
        this.fechaAtencionKey = fechaAtencionKey;
    }

    public int getEmpresaKey() {
        return empresaKey;
    }

    public void setEmpresaKey(int empresaKey) {
        this.empresaKey = empresaKey;
    }

    public int getPreexistenciaKey() {
        return preexistenciaKey;
    }

    public void setPreexistenciaKey(int preexistenciaKey) {
        this.preexistenciaKey = preexistenciaKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pacienteKey;
        hash += (int) demografiaPacienteKey;
        hash += (int) fechaSolicitudKey;
        hash += (int) fechaAtencionKey;
        hash += (int) empresaKey;
        hash += (int) preexistenciaKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrgenciasBodegaPK)) {
            return false;
        }
        UrgenciasBodegaPK other = (UrgenciasBodegaPK) object;
        if (this.pacienteKey != other.pacienteKey) {
            return false;
        }
        if (this.demografiaPacienteKey != other.demografiaPacienteKey) {
            return false;
        }
        if (this.fechaSolicitudKey != other.fechaSolicitudKey) {
            return false;
        }
        if (this.fechaAtencionKey != other.fechaAtencionKey) {
            return false;
        }
        if (this.empresaKey != other.empresaKey) {
            return false;
        }
        if (this.preexistenciaKey != other.preexistenciaKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.UrgenciasBodegaPK[ pacienteKey=" + pacienteKey + ", demografiaPacienteKey=" + demografiaPacienteKey + ", fechaSolicitudKey=" + fechaSolicitudKey + ", fechaAtencionKey=" + fechaAtencionKey + ", empresaKey=" + empresaKey + ", preexistenciaKey=" + preexistenciaKey + " ]";
    }
    
}
