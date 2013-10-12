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
@Table(name = "empresa_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresaBodega.findAll", query = "SELECT e FROM EmpresaBodega e"),
    @NamedQuery(name = "EmpresaBodega.findByEmpresaKey", query = "SELECT e FROM EmpresaBodega e WHERE e.empresaKey = :empresaKey"),
    @NamedQuery(name = "EmpresaBodega.findByNit", query = "SELECT e FROM EmpresaBodega e WHERE e.nit = :nit"),
    @NamedQuery(name = "EmpresaBodega.findByNombre", query = "SELECT e FROM EmpresaBodega e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EmpresaBodega.findByDireccion", query = "SELECT e FROM EmpresaBodega e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "EmpresaBodega.findByCantidadEmpleados", query = "SELECT e FROM EmpresaBodega e WHERE e.cantidadEmpleados = :cantidadEmpleados"),
    @NamedQuery(name = "EmpresaBodega.findByActividadComercial", query = "SELECT e FROM EmpresaBodega e WHERE e.actividadComercial = :actividadComercial"),
    @NamedQuery(name = "EmpresaBodega.findByCiudad", query = "SELECT e FROM EmpresaBodega e WHERE e.ciudad = :ciudad")})
public class EmpresaBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "empresa_key")
    private Integer empresaKey;
    @Column(name = "nit")
    private String nit;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "cantidad_empleados")
    private Integer cantidadEmpleados;
    @Column(name = "actividad_comercial")
    private String actividadComercial;
    @Column(name = "ciudad")
    private String ciudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaBodega")
    private List<PagosBodega> pagosBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaBodega")
    private List<AfiliacionesBodega> afiliacionesBodegaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaBodega")
    private List<UrgenciasBodega> urgenciasBodegaList;

    public EmpresaBodega() {
    }

    public EmpresaBodega(Integer empresaKey) {
        this.empresaKey = empresaKey;
    }

    public Integer getEmpresaKey() {
        return empresaKey;
    }

    public void setEmpresaKey(Integer empresaKey) {
        this.empresaKey = empresaKey;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(Integer cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public String getActividadComercial() {
        return actividadComercial;
    }

    public void setActividadComercial(String actividadComercial) {
        this.actividadComercial = actividadComercial;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @XmlTransient
    public List<PagosBodega> getPagosBodegaList() {
        return pagosBodegaList;
    }

    public void setPagosBodegaList(List<PagosBodega> pagosBodegaList) {
        this.pagosBodegaList = pagosBodegaList;
    }

    @XmlTransient
    public List<AfiliacionesBodega> getAfiliacionesBodegaList() {
        return afiliacionesBodegaList;
    }

    public void setAfiliacionesBodegaList(List<AfiliacionesBodega> afiliacionesBodegaList) {
        this.afiliacionesBodegaList = afiliacionesBodegaList;
    }

    @XmlTransient
    public List<UrgenciasBodega> getUrgenciasBodegaList() {
        return urgenciasBodegaList;
    }

    public void setUrgenciasBodegaList(List<UrgenciasBodega> urgenciasBodegaList) {
        this.urgenciasBodegaList = urgenciasBodegaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaKey != null ? empresaKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaBodega)) {
            return false;
        }
        EmpresaBodega other = (EmpresaBodega) object;
        if ((this.empresaKey == null && other.empresaKey != null) || (this.empresaKey != null && !this.empresaKey.equals(other.empresaKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.EmpresaBodega[ empresaKey=" + empresaKey + " ]";
    }
    
}
