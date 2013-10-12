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
public class AfiliacionesBodegaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "paciente_key")
    private int pacienteKey;
    @Basic(optional = false)
    @Column(name = "demografia_paciente_key")
    private int demografiaPacienteKey;
    @Basic(optional = false)
    @Column(name = "fecha_key")
    private long fechaKey;
    @Basic(optional = false)
    @Column(name = "empresa_key")
    private int empresaKey;
    @Basic(optional = false)
    @Column(name = "ips_key")
    private int ipsKey;

    public AfiliacionesBodegaPK() {
    }

    public AfiliacionesBodegaPK(int pacienteKey, int demografiaPacienteKey, long fechaKey, int empresaKey, int ipsKey) {
        this.pacienteKey = pacienteKey;
        this.demografiaPacienteKey = demografiaPacienteKey;
        this.fechaKey = fechaKey;
        this.empresaKey = empresaKey;
        this.ipsKey = ipsKey;
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

    public long getFechaKey() {
        return fechaKey;
    }

    public void setFechaKey(long fechaKey) {
        this.fechaKey = fechaKey;
    }

    public int getEmpresaKey() {
        return empresaKey;
    }

    public void setEmpresaKey(int empresaKey) {
        this.empresaKey = empresaKey;
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
        hash += (int) pacienteKey;
        hash += (int) demografiaPacienteKey;
        hash += (int) fechaKey;
        hash += (int) empresaKey;
        hash += (int) ipsKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfiliacionesBodegaPK)) {
            return false;
        }
        AfiliacionesBodegaPK other = (AfiliacionesBodegaPK) object;
        if (this.pacienteKey != other.pacienteKey) {
            return false;
        }
        if (this.demografiaPacienteKey != other.demografiaPacienteKey) {
            return false;
        }
        if (this.fechaKey != other.fechaKey) {
            return false;
        }
        if (this.empresaKey != other.empresaKey) {
            return false;
        }
        if (this.ipsKey != other.ipsKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.AfiliacionesBodegaPK[ pacienteKey=" + pacienteKey + ", demografiaPacienteKey=" + demografiaPacienteKey + ", fechaKey=" + fechaKey + ", empresaKey=" + empresaKey + ", ipsKey=" + ipsKey + " ]";
    }
    
}
