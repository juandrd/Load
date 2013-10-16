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
public class RemisionesBodegaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "medico_atiende_key")
    private int medicoAtiendeKey;
    @Basic(optional = false)
    @Column(name = "medico_remitente_key")
    private int medicoRemitenteKey;
    @Basic(optional = false)
    @Column(name = "paciente_key")
    private int pacienteKey;
    @Basic(optional = false)
    @Column(name = "demografia_paciente_key")
    private int demografiaPacienteKey;
    @Basic(optional = false)
    @Column(name = "fecha_remision_key")
    private long fechaRemisionKey;
    @Basic(optional = false)
    @Column(name = "fecha_atencion_key")
    private long fechaAtencionKey;
    @Basic(optional = false)
    @Column(name = "diagnostico_key")
    private int diagnosticoKey;
    @Basic(optional = false)
    @Column(name = "preexistencia_key")
    private int preexistenciaKey;
    @Basic(optional = false)
    @Column(name = "servicio_pos_key")
    private int servicioPosKey;
    @Basic(optional = false)
    @Column(name = "ips_key")
    private int ipsKey;

    public RemisionesBodegaPK() {
    }

    public RemisionesBodegaPK(int medicoAtiendeKey, int medicoRemitenteKey, int pacienteKey, int demografiaPacienteKey, long fechaRemisionKey, long fechaAtencionKey, int diagnosticoKey, int preexistenciaKey, int servicioPosKey, int ipsKey) {
        this.medicoAtiendeKey = medicoAtiendeKey;
        this.medicoRemitenteKey = medicoRemitenteKey;
        this.pacienteKey = pacienteKey;
        this.demografiaPacienteKey = demografiaPacienteKey;
        this.fechaRemisionKey = fechaRemisionKey;
        this.fechaAtencionKey = fechaAtencionKey;
        this.diagnosticoKey = diagnosticoKey;
        this.preexistenciaKey = preexistenciaKey;
        this.servicioPosKey = servicioPosKey;
        this.ipsKey = ipsKey;
    }

    public int getMedicoAtiendeKey() {
        return medicoAtiendeKey;
    }

    public void setMedicoAtiendeKey(int medicoAtiendeKey) {
        this.medicoAtiendeKey = medicoAtiendeKey;
    }

    public int getMedicoRemitenteKey() {
        return medicoRemitenteKey;
    }

    public void setMedicoRemitenteKey(int medicoRemitenteKey) {
        this.medicoRemitenteKey = medicoRemitenteKey;
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

    public long getFechaRemisionKey() {
        return fechaRemisionKey;
    }

    public void setFechaRemisionKey(long fechaRemisionKey) {
        this.fechaRemisionKey = fechaRemisionKey;
    }

    public long getFechaAtencionKey() {
        return fechaAtencionKey;
    }

    public void setFechaAtencionKey(long fechaAtencionKey) {
        this.fechaAtencionKey = fechaAtencionKey;
    }

    public int getDiagnosticoKey() {
        return diagnosticoKey;
    }

    public void setDiagnosticoKey(int diagnosticoKey) {
        this.diagnosticoKey = diagnosticoKey;
    }

    public int getPreexistenciaKey() {
        return preexistenciaKey;
    }

    public void setPreexistenciaKey(int preexistenciaKey) {
        this.preexistenciaKey = preexistenciaKey;
    }

    public int getServicioPosKey() {
        return servicioPosKey;
    }

    public void setServicioPosKey(int servicioPosKey) {
        this.servicioPosKey = servicioPosKey;
    }

    public int getIpsKey() {
        return ipsKey;
    }

    public void setIpsKey(int ipsKey) {
        this.ipsKey = ipsKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) medicoAtiendeKey;
        hash += (int) medicoRemitenteKey;
        hash += (int) pacienteKey;
        hash += (int) demografiaPacienteKey;
        hash += (int) fechaRemisionKey;
        hash += (int) fechaAtencionKey;
        hash += (int) diagnosticoKey;
        hash += (int) preexistenciaKey;
        hash += (int) servicioPosKey;
        hash += (int) ipsKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemisionesBodegaPK)) {
            return false;
        }
        RemisionesBodegaPK other = (RemisionesBodegaPK) object;
        if (this.medicoAtiendeKey != other.medicoAtiendeKey) {
            return false;
        }
        if (this.medicoRemitenteKey != other.medicoRemitenteKey) {
            return false;
        }
        if (this.pacienteKey != other.pacienteKey) {
            return false;
        }
        if (this.demografiaPacienteKey != other.demografiaPacienteKey) {
            return false;
        }
        if (this.fechaRemisionKey != other.fechaRemisionKey) {
            return false;
        }
        if (this.fechaAtencionKey != other.fechaAtencionKey) {
            return false;
        }
        if (this.diagnosticoKey != other.diagnosticoKey) {
            return false;
        }
        if (this.preexistenciaKey != other.preexistenciaKey) {
            return false;
        }
        if (this.servicioPosKey != other.servicioPosKey) {
            return false;
        }
        if (this.ipsKey != other.ipsKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.RemisionesBodegaPK[ medicoAtiendeKey=" + medicoAtiendeKey + ", medicoRemitenteKey=" + medicoRemitenteKey + ", pacienteKey=" + pacienteKey + ", demografiaPacienteKey=" + demografiaPacienteKey + ", fechaRemisionKey=" + fechaRemisionKey + ", fechaAtencionKey=" + fechaAtencionKey + ", diagnosticoKey=" + diagnosticoKey + ", preexistenciaKey=" + preexistenciaKey + ", servicioPosKey=" + servicioPosKey + ", ipsKey=" + ipsKey + " ]";
    }
    
}
