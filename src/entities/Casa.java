package entities;

public class Casa {
    private Personagem personagem;
    private Armadilha armadilha;
    private boolean ocupado = false;
    
    public Casa() {
    }

    public Casa(Personagem personagem) {
        this.personagem = personagem;
        this.armadilha = null;
        this.ocupado = true;
    }

    public Casa(Armadilha armadilha) {
        this.personagem = null;
        this.armadilha = armadilha;
        this.ocupado = true;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public Armadilha getArmadilha() {
        return armadilha;
    }

    public void removeArmadilha() {
        this.armadilha = null;
        setOcupado(false);
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        String temp = "[]";
        if (getPersonagem() != null) {
            temp = getPersonagem().toString();
        }
        if (getArmadilha() != null) {
            temp = getArmadilha().toString();
        }
        return temp;
    }
}
