import java.util.ArrayList;
import java.util.List;

public class CentralDePedidos {
    private final List<PedidoListener> ouvintes = new ArrayList<>();

    public void inscrever(PedidoListener ouvinte) {
        ouvintes.add(ouvinte);
    }

    public void publicar(String pedidoId, double valor) {
        for (PedidoListener ouvinte : ouvintes) {
            ouvinte.aoCriarPedido(pedidoId, valor);
        }
    }
}
