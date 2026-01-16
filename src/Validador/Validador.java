package Validador;

/**
 * Classe utilitária responsável pela validação de dados inseridos pelo utilizador.
 * Contém métodos estáticos para verificar formatos de NIF, matrículas, emails, etc.
 */
public class Validador {

    /**
     * Valida um número de telefone.
     * Verifica se não é nulo, se tem pelo menos 4 dígitos e se contém apenas algarismos.
     *
     * @param telefone String com o número a validar
     * @return true se for válido, false caso contrário
     */
    public static boolean validarTelefone (String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }

        if (telefone.length() < 4) {
            return false;
        }
        // Verificar se são todos digitos
        for (int i = 0; i < telefone.length(); i++){
            if (!Character.isDigit(telefone.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Valida um NIF (Número de Identificação Fiscal).
     * Deve ter exatamente 9 dígitos numéricos.
     *
     * @param nif String com o NIF
     * @return true se tiver 9 dígitos, false caso contrário
     */
    public static boolean validarNif (String nif) {
        if (nif == null || nif.isEmpty()) {
            return false;
        }
        // verificar se tem 9 digitos
        if (nif.length() != 9) {
            return false;
        }

        //verificar se são digitos
        for (int i = 0; i < nif.length(); i++) {
            if (!Character.isDigit(nif.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Valida um Número de Segurança Social (NSS).
     * Deve ter exatamente 11 dígitos numéricos.
     *
     * @param nss String com o NSS
     * @return true se tiver 11 dígitos, false caso contrário
     */
    public static boolean validarNss (String nss) {
        if (nss == null || nss.isEmpty()) {
            return false;
        }
        //verificar se tem 11 digitos
        if (nss.length() != 11) {
            return false;
        }

        //verificar se sao digitos
        for (int i = 0; i < nss.length(); i++) {
            if (!Character.isDigit(nss.charAt(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * Valida um Número de Identificação Civil (CC).
     * Verifica se tem pelo menos 8 caracteres e se os primeiros 8 são dígitos.
     *
     * @param numId String com o número de identificação
     * @return true se respeitar o formato mínimo, false caso contrário
     */
    public static boolean validarNumeroIdentificacao (String numId) {
        if (numId == null || numId.isEmpty()) {
            return false;
        }

        //verificar comprimento (12 caracteres: 8 digitos + 4 caracteres)
        if (numId.length() < 8) {
            return false;
        }
        // verificar se os primeiros 8 sao digitos
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(numId.charAt(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * Valida uma matrícula automóvel.
     * Aceita formatos com ou sem hífen, desde que tenha 6 caracteres alfanuméricos.
     *
     * @param matricula String com a matrícula
     * @return true se tiver 6 caracteres válidos, false caso contrário
     */
    public static boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            return false;
        }

        // 1. Limpar: Removemos hifens, espaços e passamos a maiúsculas temporariamente
        String matriculaLimpa = matricula.toUpperCase().replaceAll("[^A-Z0-9]", "");

        // 2. Verificar: Se tem exatamente 6 caracteres alfanuméricos
        return matriculaLimpa.length() == 6;
    }

    /**
     * Valida uma Carta de Condução.
     * Verifica o formato: 2 letras seguidas de 6 dígitos.
     *
     * @param carta String com o número da carta
     * @return true se respeitar o padrão LL-DDDDDD (ou LLDDDDDD), false caso contrário
     */
    public static boolean validarCartaConducao(String carta) {
        if (carta == null) return false;

        // 1. Limpar: Remove hifens e espaços, passa a maiúsculas
        String limpa = carta.toUpperCase().replaceAll("[^A-Z0-9]", "");

        // 2. Verificar formato: Deve ter exatamente 2 Letras + 6 Dígitos
        return limpa.matches("[A-Z]{2}[0-9]{6}");
    }

    /**
     * Valida um endereço de e-mail de forma simples.
     * Verifica apenas se contém o caracter '@' e não está vazio.
     *
     * @param email String com o e-mail
     * @return true se contiver '@', false caso contrário
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return true; // E-mail pode ser opcional
        }

        // Verificar se contem @
        int posArroba = email.indexOf('@');
        if (posArroba < 1) {
            return false;
        }

        return true;
    }

    /**
     * Valida o ano de fabrico de uma viatura.
     *
     * @param ano Ano a validar
     * @return true se estiver entre 1900 e 2026
     */
    public static boolean validarAnoFabrico (int ano) {
        return ano >= 1900 && ano <= 2026;
    }

    /**
     * Valida o número de lugares de uma viatura.
     *
     * @param lugares Número de lugares
     * @return true se estiver entre 1 e 9
     */
    public static boolean validarLugares(int lugares) {
        return lugares >= 1 && lugares <= 9;
    }

    /**
     * Valida uma distância em quilómetros.
     *
     * @param kms Valor da distância
     * @return true se for maior que 0 e menor que 10000
     */
    public static boolean validarKms(double kms){
        return kms > 0 && kms < 10000;
    }

    /**
     * Verifica se uma string cumpre um comprimento mínimo.
     *
     * @param texto String a verificar
     * @param minimo Número mínimo de caracteres
     * @return true se o comprimento for igual ou superior ao mínimo
     */
    public static boolean validarComprimentoMinimo(String texto, int minimo) {
        if (texto == null) {
            return false;
        }

        return texto.trim().length() >= minimo;
    }

    /**
     * Formata uma matrícula para o padrão XX-XX-XX.
     * Adiciona os hifens automaticamente caso não existam.
     *
     * @param matricula Matrícula original
     * @return Matrícula formatada em maiúsculas com hifens
     */
    public static String formatarMatricula(String matricula) {
        if (matricula == null) {
            return "";
        }

        // 1. Passar para maiúsculas
        String m = matricula.toUpperCase();

        // 2. Remover tudo o que não interessa
        String limpa = m.replaceAll("[^A-Z0-9]", "");

        // 3. Se tiver o tamanho certo (6), formatamos XX-XX-XX
        if (limpa.length() == 6) {
            return limpa.substring(0, 2) + "-" + limpa.substring(2, 4) + "-" + limpa.substring(4, 6);
        }

        return m;
    }

    /**
     * Formata uma carta de condução para o padrão XX-XXXXXX.
     *
     * @param carta Carta original
     * @return Carta formatada em maiúsculas com hífen
     */
    public static String formatarCartaConducao(String carta) {
        if (carta == null) return "";

        String limpa = carta.toUpperCase().replaceAll("[^A-Z0-9]", "");

        if (limpa.length() == 8) {
            return limpa.substring(0, 2) + "-" + limpa.substring(2);
        }

        return carta.toUpperCase();
    }

    /**
     * Formata um email para letras minúsculas e remove espaços.
     *
     * @param email Email original
     * @return Email formatado
     */
    public static String formatarEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }

    // Métodos para obter mensagens de erro (Getters simples)

    public static String getMensagemErroTelefone() {
        return "Telefone invalido! Deve ter 4 digitos no mínimo.";
    }

    public static String getMensagemErroNif() {
        return "NIF invalido! Deve ter 9 digitos.";
    }

    public static String gerMensagemErroNss() {
        return "N. Segurança Social invalido! Deve ter 11 digitos.";
    }

    public static String getMensagemErroMatricula() {
        return "Matricula invalida! Formato: AA-00-AA, 00-AA-00, etc.";
    }

    public static String getMensagemErroCarta() {
        return "Carta de condução invalida! Formato: XX-123456 (2 letras + 6 digitos).";
    }
    public static String getMensagemErroEmail(){
        return "E-mail invalido! Formato: exemplo@gmail.com";
    }

    public static String getMensagemErroAno() {
        return "Ano invalido! Deve estar entre 1900 e 2026.";
    }

    public static String getMensagemErroLugares() {
        return "Número de lugares invalido! Deve estar entre 1 e 9.";
    }

    public static String getMensagemErroKms(){
        return "Distância invalida! Deve ser maior que 0 e menor que 10000 km.";
    }

    public static String getMensagemErroNumId(){
        return "N. Identificação invalido! Deve ter pelo menos 8 digitos.";
    }

    public static String getMensagemErroMorada(){
        return "Morada deve ter no minimo 5 caracteres.";
    }




}
