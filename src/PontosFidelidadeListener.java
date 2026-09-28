public class PontosFidelidadeListener implements PedidoListener {
    @Override
    public void aoCriarPedido(String pedidoId, double valor) {
        int pontos = (int) valor;
        System.out.println("PontosFidelidadeListener: " + pontos + " pontos adicionados ao pedido " + pedidoId);
    }
}
