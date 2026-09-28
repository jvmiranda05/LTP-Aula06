import java.util.Random;

public class FreteInstavel implements ServicoFrete {
    private final Random sorte = new Random();

    @Override
    public double calcular(String cep) throws Exception {
        if (sorte.nextInt(10) < 6) {
            throw new Exception("Frete fora do ar!");
        }
        return 25.0;
    }
}
