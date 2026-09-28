public class EmailListener implements PedidoListener {
    @Override
    public void aoCriarPedido(String pedidoId, double valor) {
        System.out.println("EmailListener: e-mail enviado para o pedido " + pedidoId);
    }
}
