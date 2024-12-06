public interface Reservavel {

    void reservar(String idHospede) throws Exception, CapacidadeExcedidaException;
}
