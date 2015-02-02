package Model;

public class Apartment {
	private String codigo="";
	private String nombre="";
	private String numeroCastral="";
	private String direccion="";
	private String poblacion="";
	private String servicioBlanco="0";
	private String ambientes="0";
	private String habitaciones="0";
	private String baños="0";
	private String camas="0";
	private String metrosTerreno="0";
	private String metrosEdificados="0";
	private String alquiler="0";
	private String venta="0";
	private String dueñoPesos="0";
	private String dueñoDolares="0";
	private String inmobiliariaPesos="0";
	private String inmobiliariaDolares="0";
	private String parrilla="0";
	private String parque="0";
	private String telefono="0";
	private String lavadero="0";
	private String jovenes="0";
	private String cable="0";
	private String aireAcondicionado="0";
	private String alarma="0";
	private String servicios="0";
	private String internet="0";
	private String heladera="0";
	private String freezer="0";
	private String microondas="0";
	private String cafetera="0";
	private String tostadora="0";
	private String gasNatural="0";
	private String garaje="0";
	private String entradaAuto="0";
	private String nombreDueño="";
	private String tipoCuenta="";
	private String telefonoDueño="";
	private String numeroCuenta="";
	private String cdu="";
	private String correo="";
	private String alquilerTemporario="0";
	private String banco="";
	private String permuta="";
	private String descripcion="";
	private String precioDiaPesos="";
	private String precioSemanaPesos="";
	private String precioQuincenaPesos="";
	private String precioMesPesos="";
	private String precioAñoPesos="";
	private String precioDiaDolares="";
	private String precioSemanaDolares="";
	private String precioQuincenaDolares="";
	private String precioMesDolares="";
	private String precioAñoDolares="";
	private String[] fotos;
	private String primeraQuincenaDiciembre;
	private String primeraQuincenaEnero;
	private String primeraQuincenaFebrero;
	private String primeraQuincenaMarzo;
	private String primeraQuincenaOtro;
	private String segundaQuincenaDiciembre;
	private String segundaQuincenaEnero;
	private String segundaQuincenaFebrero;
	private String segundaQuincenaMarzo;
	private String segundaQuincenaOtro;
	private String mesOtro;
	private String semanaDiciembre;
	private String semanaEnero;
	private String semanaFebrero;
	private String semanaMarzo;
	private String semanaOtro;
	private String diaDiciembre;
	private String diaEnero;
	private String diaFebrero;
	private String diaMarzo;
	private String diaOtro;
	private String sabanaDosPlazas;
	private String sabanaUnaPlaza;
	private String toallas;
        private String wifi="0";
        private String mascotas="0";
        private String rejas="0";
        private String estacionamiento="0";
        private String estacionamientoTechado="0";
        private String ventilador="0";
        private String calefon="0";
        private String estufa="0";
        private String termotanque="0";
        private String documento="";
        private String codigoInmobiliaria="0";
        private String codigoArgenprop="0";
        private String tipoCasa="0";
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeroCastral() {
		return numeroCastral;
	}
	public void setNumeroCastral(String numeroCastral) {
		this.numeroCastral = numeroCastral;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getServicioBlanco() {
		return servicioBlanco;
	}
	public void setServicioBlanco(String servicioBlanco) {
		this.servicioBlanco = servicioBlanco;
	}
	public String getNombreDueño() {
		return nombreDueño;
	}
	public void setNombreDueño(String nombreDueño) {
		this.nombreDueño = nombreDueño;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getTelefonoDueño() {
		return telefonoDueño;
	}
	public void setTelefonoDueño(String telefonoDueño) {
		this.telefonoDueño = telefonoDueño;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getCdu() {
		return cdu;
	}
	public void setCdu(String cdu) {
		this.cdu = cdu;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String[] getFotos() {
		return fotos;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAlquiler() {
		return alquiler;
	}
	public void setAlquiler(String alquiler) {
		this.alquiler = alquiler;
	}
	public String getVenta() {
		return venta;
	}
	public void setVenta(String venta) {
		this.venta = venta;
	}
	public String getDueñoPesos() {
		return dueñoPesos;
	}
	public void setDueñoPesos(String dueñoPesos) {
		this.dueñoPesos = dueñoPesos;
	}
	public String getDueñoDolares() {
		return dueñoDolares;
	}
	public void setDueñoDolares(String dueñoDolares) {
		this.dueñoDolares = dueñoDolares;
	}
	public String getInmobiliariaPesos() {
		return inmobiliariaPesos;
	}
	public void setInmobiliariaPesos(String inmobiliariaPesos) {
		this.inmobiliariaPesos = inmobiliariaPesos;
	}
	public String getInmobiliariaDolares() {
		return inmobiliariaDolares;
	}
	public void setInmobiliariaDolares(String inmobiliariaDolares) {
		this.inmobiliariaDolares = inmobiliariaDolares;
	}
	public String getParrilla() {
		return parrilla;
	}
	public void setParrilla(String parrilla) {
		this.parrilla = parrilla;
	}
	public String getParque() {
		return parque;
	}
	public void setParque(String parque) {
		this.parque = parque;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLavadero() {
		return lavadero;
	}
	public void setLavadero(String lavadero) {
		this.lavadero = lavadero;
	}
	public String getJovenes() {
		return jovenes;
	}
	public void setJovenes(String jovenes) {
		this.jovenes = jovenes;
	}
	public String getCable() {
		return cable;
	}
	public void setCable(String cable) {
		this.cable = cable;
	}
	public String getAireAcondicionado() {
		return aireAcondicionado;
	}
	public void setAireAcondicionado(String aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}
	public String getAlarma() {
		return alarma;
	}
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}
	public String getServicios() {
		return servicios;
	}
	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
	public String getHeladera() {
		return heladera;
	}
	public void setHeladera(String heladera) {
		this.heladera = heladera;
	}
	public String getFreezer() {
		return freezer;
	}
	public void setFreezer(String freezer) {
		this.freezer = freezer;
	}
	public String getMicroondas() {
		return microondas;
	}
	public void setMicroondas(String microondas) {
		this.microondas = microondas;
	}
	public String getCafetera() {
		return cafetera;
	}
	public void setCafetera(String cafetera) {
		this.cafetera = cafetera;
	}
	public String getTostadora() {
		return tostadora;
	}
	public void setTostadora(String tostadora) {
		this.tostadora = tostadora;
	}
	public String getGasNatural() {
		return gasNatural;
	}
	public void setGasNatural(String gasNatural) {
		this.gasNatural = gasNatural;
	}
	public String getGaraje() {
		return garaje;
	}
	public void setGaraje(String garaje) {
		this.garaje = garaje;
	}
	public String getEntradaAuto() {
		return entradaAuto;
	}
	public void setEntradaAuto(String entradaAuto) {
		this.entradaAuto = entradaAuto;
	}
	public String getInternet() {
		return internet;
	}
	public void setInternet(String internet) {
		this.internet = internet;
	}
	public String getAmbientes() {
		return ambientes;
	}
	public void setAmbientes(String ambientes) {
		this.ambientes = ambientes;
	}
	public String getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(String habitaciones) {
		this.habitaciones = habitaciones;
	}
	public String getBaños() {
		return baños;
	}
	public void setBaños(String baños) {
		this.baños = baños;
	}
	public String getCamas() {
		return camas;
	}
	public void setCamas(String camas) {
		this.camas = camas;
	}
	public String getMetrosTerreno() {
		return metrosTerreno;
	}
	public void setMetrosTerreno(String metrosTerreno) {
		this.metrosTerreno = metrosTerreno;
	}
	public String getMetrosEdificados() {
		return metrosEdificados;
	}
	public void setMetrosEdificados(String metrosEdificados) {
		this.metrosEdificados = metrosEdificados;
	}
	public String getPermuta() {
		return permuta;
	}
	public void setPermuta(String permuta) {
		this.permuta = permuta;
	}
	public String getAlquilerTemporario() {
		return alquilerTemporario;
	}
	public void setAlquilerTemporario(String alquilerTemporario) {
		this.alquilerTemporario = alquilerTemporario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrecioDiaPesos() {
		return precioDiaPesos;
	}
	public void setPrecioDiaPesos(String precioDiaPesos) {
		this.precioDiaPesos = precioDiaPesos;
	}
	public String getPrecioSemanaPesos() {
		return precioSemanaPesos;
	}
	public void setPrecioSemanaPesos(String precioSemanaPesos) {
		this.precioSemanaPesos = precioSemanaPesos;
	}
	public String getPrecioQuincenaPesos() {
		return precioQuincenaPesos;
	}
	public void setPrecioQuincenaPesos(String precioQuincenaPesos) {
		this.precioQuincenaPesos = precioQuincenaPesos;
	}
	public String getPrecioMesPesos() {
		return precioMesPesos;
	}
	public void setPrecioMesPesos(String precioMesPesos) {
		this.precioMesPesos = precioMesPesos;
	}
	public String getPrecioAñoPesos() {
		return precioAñoPesos;
	}
	public void setPrecioAñoPesos(String precioAñoPesos) {
		this.precioAñoPesos = precioAñoPesos;
	}
	public String getPrecioDiaDolares() {
		return precioDiaDolares;
	}
	public void setPrecioDiaDolares(String precioDiaDolares) {
		this.precioDiaDolares = precioDiaDolares;
	}
	public String getPrecioSemanaDolares() {
		return precioSemanaDolares;
	}
	public void setPrecioSemanaDolares(String precioSemanaDolares) {
		this.precioSemanaDolares = precioSemanaDolares;
	}
	public String getPrecioQuincenaDolares() {
		return precioQuincenaDolares;
	}
	public void setPrecioQuincenaDolares(String precioQuincenaDolares) {
		this.precioQuincenaDolares = precioQuincenaDolares;
	}
	public String getPrecioMesDolares() {
		return precioMesDolares;
	}
	public void setPrecioMesDolares(String precioMesDolares) {
		this.precioMesDolares = precioMesDolares;
	}
	public String getPrecioAñoDolares() {
		return precioAñoDolares;
	}
	public void setPrecioAñoDolares(String precioAñoDolares) {
		this.precioAñoDolares = precioAñoDolares;
	}
	public String getPrimeraQuincenaDiciembre() {
		return primeraQuincenaDiciembre;
	}
	public void setPrimeraQuincenaDiciembre(String primeraQuincenaDiciembre) {
		this.primeraQuincenaDiciembre = primeraQuincenaDiciembre;
	}
	public String getPrimeraQuincenaEnero() {
		return primeraQuincenaEnero;
	}
	public void setPrimeraQuincenaEnero(String primeraQuincenaEnero) {
		this.primeraQuincenaEnero = primeraQuincenaEnero;
	}
	public String getPrimeraQuincenaFebrero() {
		return primeraQuincenaFebrero;
	}
	public void setPrimeraQuincenaFebrero(String primeraQuincenaFebrero) {
		this.primeraQuincenaFebrero = primeraQuincenaFebrero;
	}
	public String getPrimeraQuincenaMarzo() {
		return primeraQuincenaMarzo;
	}
	public void setPrimeraQuincenaMarzo(String primeraQuincenaMarzo) {
		this.primeraQuincenaMarzo = primeraQuincenaMarzo;
	}
	public String getPrimeraQuincenaOtro() {
		return primeraQuincenaOtro;
	}
	public void setPrimeraQuincenaOtro(String primeraQuincenaOtro) {
		this.primeraQuincenaOtro = primeraQuincenaOtro;
	}
	public String getSegundaQuincenaDiciembre() {
		return segundaQuincenaDiciembre;
	}
	public void setSegundaQuincenaDiciembre(String segundaQuincenaDiciembre) {
		this.segundaQuincenaDiciembre = segundaQuincenaDiciembre;
	}
	public String getSegundaQuincenaEnero() {
		return segundaQuincenaEnero;
	}
	public void setSegundaQuincenaEnero(String segundaQuincenaEnero) {
		this.segundaQuincenaEnero = segundaQuincenaEnero;
	}
	public String getSegundaQuincenaFebrero() {
		return segundaQuincenaFebrero;
	}
	public void setSegundaQuincenaFebrero(String segundaQuincenaFebrero) {
		this.segundaQuincenaFebrero = segundaQuincenaFebrero;
	}
	public String getSegundaQuincenaMarzo() {
		return segundaQuincenaMarzo;
	}
	public void setSegundaQuincenaMarzo(String segundaQuincenaMarzo) {
		this.segundaQuincenaMarzo = segundaQuincenaMarzo;
	}
	public String getSegundaQuincenaOtro() {
		return segundaQuincenaOtro;
	}
	public void setSegundaQuincenaOtro(String segundaQuincenaOtro) {
		this.segundaQuincenaOtro = segundaQuincenaOtro;
	}
	public String getMesOtro() {
		return mesOtro;
	}
	public void setMesOtro(String mesOtro) {
		this.mesOtro = mesOtro;
	}
	public String getSemanaDiciembre() {
		return semanaDiciembre;
	}
	public void setSemanaDiciembre(String semanaDiciembre) {
		this.semanaDiciembre = semanaDiciembre;
	}
	public String getSemanaEnero() {
		return semanaEnero;
	}
	public void setSemanaEnero(String semanaEnero) {
		this.semanaEnero = semanaEnero;
	}
	public String getSemanaFebrero() {
		return semanaFebrero;
	}
	public void setSemanaFebrero(String semanaFebrero) {
		this.semanaFebrero = semanaFebrero;
	}
	public String getSemanaMarzo() {
		return semanaMarzo;
	}
	public void setSemanaMarzo(String semanaMarzo) {
		this.semanaMarzo = semanaMarzo;
	}
	public String getSemanaOtro() {
		return semanaOtro;
	}
	public void setSemanaOtro(String semanaOtro) {
		this.semanaOtro = semanaOtro;
	}
	public String getDiaDiciembre() {
		return diaDiciembre;
	}
	public void setDiaDiciembre(String diaDiciembre) {
		this.diaDiciembre = diaDiciembre;
	}
	public String getDiaEnero() {
		return diaEnero;
	}
	public void setDiaEnero(String diaEnero) {
		this.diaEnero = diaEnero;
	}
	public String getDiaFebrero() {
		return diaFebrero;
	}
	public void setDiaFebrero(String diaFebrero) {
		this.diaFebrero = diaFebrero;
	}
	public String getDiaMarzo() {
		return diaMarzo;
	}
	public void setDiaMarzo(String diaMarzo) {
		this.diaMarzo = diaMarzo;
	}
	public String getDiaOtro() {
		return diaOtro;
	}
	public void setDiaOtro(String diaOtro) {
		this.diaOtro = diaOtro;
	}
	public String getSabanaDosPlazas() {
		return sabanaDosPlazas;
	}
	public void setSabanaDosPlazas(String sabanaDosPlazas) {
		this.sabanaDosPlazas = sabanaDosPlazas;
	}
	public String getSabanaUnaPlaza() {
		return sabanaUnaPlaza;
	}
	public void setSabanaUnaPlaza(String sabanaUnaPlaza) {
		this.sabanaUnaPlaza = sabanaUnaPlaza;
	}
	public String getToallas() {
		return toallas;
	}
	public void setToallas(String toallas) {
		this.toallas = toallas;
	}

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }

    /**
     * @return the wifi
     */
    public String getWifi() {
        return wifi;
    }

    /**
     * @param wifi the wifi to set
     */
    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    /**
     * @return the mascotas
     */
    public String getMascotas() {
        return mascotas;
    }

    /**
     * @param mascotas the mascotas to set
     */
    public void setMascotas(String mascotas) {
        this.mascotas = mascotas;
    }

    /**
     * @return the rejas
     */
    public String getRejas() {
        return rejas;
    }

    /**
     * @param rejas the rejas to set
     */
    public void setRejas(String rejas) {
        this.rejas = rejas;
    }

    /**
     * @return the estacionamiento
     */
    public String getEstacionamiento() {
        return estacionamiento;
    }

    /**
     * @param estacionamiento the estacionamiento to set
     */
    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    /**
     * @return the estacionamientoTechado
     */
    public String getEstacionamientoTechado() {
        return estacionamientoTechado;
    }

    /**
     * @param estacionamientoTechado the estacionamientoTechado to set
     */
    public void setEstacionamientoTechado(String estacionamientoTechado) {
        this.estacionamientoTechado = estacionamientoTechado;
    }

    /**
     * @return the ventilador
     */
    public String getVentilador() {
        return ventilador;
    }

    /**
     * @param ventilador the ventilador to set
     */
    public void setVentilador(String ventilador) {
        this.ventilador = ventilador;
    }

    /**
     * @return the calefon
     */
    public String getCalefon() {
        return calefon;
    }

    /**
     * @param calefon the calefon to set
     */
    public void setCalefon(String calefon) {
        this.calefon = calefon;
    }

    /**
     * @return the estufa
     */
    public String getEstufa() {
        return estufa;
    }

    /**
     * @param estufa the estufa to set
     */
    public void setEstufa(String estufa) {
        this.estufa = estufa;
    }

    /**
     * @return the termotanque
     */
    public String getTermotanque() {
        return termotanque;
    }

    /**
     * @param termotanque the termotanque to set
     */
    public void setTermotanque(String termotanque) {
        this.termotanque = termotanque;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the codigoInmobiliaria
     */
    public String getCodigoInmobiliaria() {
        return codigoInmobiliaria;
    }

    /**
     * @param codigoInmobiliaria the codigoInmobiliaria to set
     */
    public void setCodigoInmobiliaria(String codigoInmobiliaria) {
        this.codigoInmobiliaria = codigoInmobiliaria;
    }

    /**
     * @return the codigoArgenprop
     */
    public String getCodigoArgenprop() {
        return codigoArgenprop;
    }

    /**
     * @param codigoArgenprop the codigoArgenprop to set
     */
    public void setCodigoArgenprop(String codigoArgenprop) {
        this.codigoArgenprop = codigoArgenprop;
    }

    /**
     * @return the tipoCasa
     */
    public String getTipoCasa() {
        return tipoCasa;
    }

    /**
     * @param tipoCasa the tipoCasa to set
     */
    public void setTipoCasa(String tipoCasa) {
        this.tipoCasa = tipoCasa;
    }
	
}
