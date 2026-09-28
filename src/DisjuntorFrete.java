public class DisjuntorFrete implements ServicoFrete {
    private static final double FALLBACK = 19.90;

    private final ServicoFrete servicoReal;
    private Estado estado = Estado.FECHADO;
    private int falhasSeguidas;
    private int chamadasAberto;

    public DisjuntorFrete(ServicoFrete servicoReal) {
        this.servicoReal = servicoReal;
    }

    @Override
    public double calcular(String cep) throws Exception {
        if (estado == Estado.ABERTO) {
            chamadasAberto++;
            double valor = FALLBACK;
            if (chamadasAberto >= 5) {
                estado = Estado.MEIO_ABERTO;
                chamadasAberto = 0;
            }
            return valor;
        }

        if (estado == Estado.MEIO_ABERTO) {
            try {
                double valor = servicoReal.calcular(cep);
                estado = Estado.FECHADO;
                falhasSeguidas = 0;
                return valor;
            } catch (Exception e) {
                estado = Estado.ABERTO;
                chamadasAberto = 0;
                return FALLBACK;
            }
        }

        try {
            return tentarComBackoff(cep);
        } catch (Exception e) {
            falhasSeguidas++;
            if (falhasSeguidas >= 3) {
                estado = Estado.ABERTO;
                chamadasAberto = 0;
            }
            return FALLBACK;
        }
    }

    private double tentarComBackoff(String cep) throws Exception {
        try {
            return servicoReal.calcular(cep);
        } catch (Exception primeira) {
            Thread.sleep(100);
            try {
                return servicoReal.calcular(cep);
            } catch (Exception segunda) {
                Thread.sleep(200);
                return servicoReal.calcular(cep);
            }
        }
    }

    public Estado getEstado() {
        return estado;
    }
}
