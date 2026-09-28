public class Main {
    public static void main(String[] args) throws Exception {
        ServicoFrete freteReal = new FreteInstavel();
        DisjuntorFrete disjuntor = new DisjuntorFrete(freteReal);

        CentralDePedidos central = new CentralDePedidos();
        central.inscrever(new FreteListener(disjuntor));
        central.inscrever(new EmailListener());
        central.inscrever(new PontosFidelidadeListener());

        central.publicar("PED-001", 150.0);
        central.publicar("PED-002", 320.0);

        for (int i = 1; i <= 20; i++) {
            double valor;
            try {
                valor = disjuntor.calcular("06700000");
            } catch (Exception e) {
                valor = 19.90;
            }
            System.out.println("[" + disjuntor.getEstado() + "] frete = " + valor);
        }
    }
}
