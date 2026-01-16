package Validador;

public class Validador {
    // Validar um numero de telefone
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

        // 1. Limpar: Removemos hifens, espaços e passamos a maiúsculas temporariamente
        // A expressão "[^A-Z0-9]" significa "tudo o que NÃO for letra (A-Z) ou número (0-9)"
        String matriculaLimpa = matricula.toUpperCase().replaceAll("[^A-Z0-9]", "");

        // 2. Verificar: Se tem exatamente 6 caracteres alfanuméricos
        return matriculaLimpa.length() == 6;
    }

    // Validar carta de condução

    public static boolean validarCartaConducao(String carta) {
        if (carta == null) return false;

        // 1. Limpar: Remove hifens e espaços, passa a maiúsculas
        String limpa = carta.toUpperCase().replaceAll("[^A-Z0-9]", "");

        // 2. Verificar formato: Deve ter exatamente 2 Letras + 6 Dígitos
        // A expressão "[A-Z]{2}[0-9]{6}" garante isso.
        return limpa.matches("[A-Z]{2}[0-9]{6}");
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

    //Validar se uma string tem o comprimento minimo
    public static boolean validarComprimentoMinimo(String texto, int minimo) {
        if (texto == null) {
            return false;
        }

        return texto.trim().length() >= minimo;
    }

    // Formata matricula (adiciona hifens e mete em maiusculas)
    public static String formatarMatricula(String matricula) {
        if (matricula == null) {
            return "";
        }

        // 1. Passar para maiúsculas
        String m = matricula.toUpperCase();

        // 2. Remover tudo o que não interessa (espaços, traços antigos, pontos)
        // Ficamos apenas com os 6 caracteres (ex: "aa 00 aa" vira "AA00AA")
        String limpa = m.replaceAll("[^A-Z0-9]", "");

        // 3. Se tiver o tamanho certo (6), formatamos XX-XX-XX
        if (limpa.length() == 6) {
            return limpa.substring(0, 2) + "-" + limpa.substring(2, 4) + "-" + limpa.substring(4, 6);
        }

        // Se não tiver 6 digitos (caso algo falhe), devolvemos a original em maiúsculas
        return m;
    }

    // Formata matricula (adiciona hifens e mete em maiusculas)
    public static String formatarCartaConducao(String carta) {
        if (carta == null) return "";

        // 1. Limpar e Uppercase
        String limpa = carta.toUpperCase().replaceAll("[^A-Z0-9]", "");

        // 2. Se tiver o formato correto (8 chars), adiciona o hífen
        if (limpa.length() == 8) {
            return limpa.substring(0, 2) + "-" + limpa.substring(2);
        }

        return carta.toUpperCase();
    }

    // Formatar email para minusculas
    public static String formatarEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }

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
