/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "preexistencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preexistencias.findAll", query = "SELECT p FROM Preexistencias p"),
    @NamedQuery(name = "Preexistencias.findByIDUsuario", query = "SELECT p FROM Preexistencias p WHERE p.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "Preexistencias.findByFamiliar", query = "SELECT p FROM Preexistencias p WHERE p.familiar = :familiar"),
    @NamedQuery(name = "Preexistencias.findByEnfermedad", query = "SELECT p FROM Preexistencias p WHERE p.enfermedad = :enfermedad")})
public class Preexistencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Usuario")
    private String iDUsuario;
    @Column(name = "Familiar")
    private String familiar;
    @Column(name = "Enfermedad")
    private String enfermedad;

    public Preexistencias() {
    }

    public Preexistencias(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDUsuario != null ? iDUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preexistencias)) {
            return false;
        }
        Preexistencias other = (Preexistencias) object;
        if ((this.iDUsuario == null && other.iDUsuario != null) || (this.iDUsuario != null && !this.iDUsuario.equals(other.iDUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Preexistencias[ iDUsuario=" + iDUsuario + " ]";
    }
    
}
