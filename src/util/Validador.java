package util;

public class Validador {
    // Validar um numero de telefone (9 digitos)
    public static boolean validarTelefone (String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }

        //verificar se tem 9 digitos
        if (telefone.length() != 9){
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

    //Validar niff (9 digitos)
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

    //Validar Numero da segurança social.(11 digitos)
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

    //Validar numero identificação civil - cc (8 digitos + 1 letra + 2 letras e 1 numero -> Formato: 12345678 X AB1)

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
    // Validar matricula
    public static boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            return false;
        }
        // transformar a string em maiúsculas.
        matricula = matricula.toUpperCase();
        return true;
    }

    // Validar carta de condução

    public static boolean validarCartaConducao(String carta) {
        if (carta == null || carta.isEmpty()) {
            return false;
        }


        return true;
    }

    // Validar endereço de e-mail.

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

    // Validar ano de fabrico
    public static boolean validarAnoFabrico (int ano) {
        return ano >= 1900 && ano <= 2026;
    }

    // Valida o numero de lugares.
    public static boolean validarLugares(int lugares) {
        return lugares >= 1 && lugares <= 9;
    }

    // Validar distancia em km.
    public static boolean validarKms(double kms){
        return kms > 0 && kms < 10000;
    }

    //Validar custo/valor
    public static boolean validarValor(double valor) {
        return valor > 0;
    }

    //Validar texto obrigatorio
    public static boolean validarTextoObrigatorio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    //Validar se uma string tem o comprimento minimo
    public static boolean validarComprimentoMinimo(String texto, int minimo) {
        if (texto == null) {
            return false;
        }

        return texto.trim().length() >= minimo;
    }

    //Formata um numero de telefone (adiciona espaços)

    public static String formatarTelefone(String telefone) {
        if (telefone == null) {
            return "";
        }
        if (telefone.length() == 9) {
            return telefone.substring(0, 3) + " " + telefone.substring(3, 6) + " " + telefone.substring(6);
        }
        return telefone;
    }

    //Formata matricula (adiciona hifens)
    public static String formatarMatricula(String matricula) {
        if (matricula == null) {
            return "";
        }
        if (matricula.length() == 6) {
            return matricula.substring(0, 2) + "-" + matricula.substring(2, 4) + "-" + matricula.substring(4);
        }
        return matricula;
    }

    public static String getMensagemErroTelefone() {
        return "Telefone invalido! Deve ter 9 digitos.";
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
