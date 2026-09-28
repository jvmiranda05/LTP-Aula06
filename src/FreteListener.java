public class FreteListener implements PedidoListener {
    private final ServicoFrete servicoFrete;

    public FreteListener(ServicoFrete servicoFrete) {
        this.servicoFrete = servicoFrete;
    }

    @Override
    public void aoCriarPedido(String pedidoId, double valor) {
        try {
            double frete = servicoFrete.calcular("06700000");
            System.out.println("FreteListener: pedido " + pedidoId + " - frete = " + frete);
        } catch (Exception e) {
            System.out.println("FreteListener: pedido " + pedidoId + " - frete indisponível");
        }
    }
}
