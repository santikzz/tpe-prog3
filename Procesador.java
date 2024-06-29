package practico_especial;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String idProcesador;
    private String codigoProcesador;
    private Boolean estaRefrigerado;
    private Integer anioFuncionamiento;

    private Integer tiempoEjecucionAcumulado;

    List<Tarea> tareasAsignadas;

    public Procesador(String idProcesador, String codigoProcesador, Boolean estaRefrigerado, Integer anioFuncionamiento) {
        this.idProcesador = idProcesador;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;

        this.tareasAsignadas = new ArrayList<>();
        this.tiempoEjecucionAcumulado = 0;
    }

    public void popTarea(Tarea tarea){
        this.tareasAsignadas.remove(tarea);
    }

    public boolean esRefrigerado(){
        return this.estaRefrigerado;
    }

    public boolean tieneTareas(){
        return !this.tareasAsignadas.isEmpty();
    }

    public Integer getTiempoEjecucionAcumulado(){
        return this.tiempoEjecucionAcumulado;
    }

    public Tarea getUltimaTarea(){
        return this.tareasAsignadas.get(this.tareasAsignadas.size()-1);
    }

    public String getId(){
        return this.idProcesador;
    }

    public void add(Tarea t){
        this.tareasAsignadas.add(t);
        //this.tiempoEjecucionAcumulado += tarea.getTiempoEjecucion();
    }

    public void remove(Tarea t){
        this.tareasAsignadas.remove(t);
    }

    public int getTiempoEjecucion(){
        int tiempo = 0;
        for(Tarea t : this.tareasAsignadas){
            tiempo += t.getTiempoEjecucion();
        }
        return tiempo;
    }

    public boolean esValido(int maxTiempoEjecucion){

        int critCount = 0;
        int tiempoAcumulado = 0;

        for(Tarea t : this.tareasAsignadas){

            if(t.esCritica())
                critCount++;

            tiempoAcumulado += t.getTiempoEjecucion();

            if(critCount >= 2)
                return false;

            if(!this.estaRefrigerado && tiempoAcumulado >= maxTiempoEjecucion)
                return false;

        }
        return true;

    }

}
