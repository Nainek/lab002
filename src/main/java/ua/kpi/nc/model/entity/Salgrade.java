package ua.kpi.nc.model.entity;

public class Salgrade {

    private Long grade;
    private Integer losal;
    private Integer hisal;

    public Salgrade() {
    }

    public static class Builder implements IBuilder<Salgrade> {
        Salgrade sal = new Salgrade();

        public Builder setGrade(Long grade){
            sal.grade = grade;
            return this;
        }

        public Builder setLosal(Integer losal){
            sal.losal = losal;
            return this;
        }

        public Builder setHisal(Integer hisal){
            sal.hisal = hisal;
            return this;
        }

        public Salgrade build() {
            return sal;
        }
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Integer getLosal() {
        return losal;
    }

    public void setLosal(Integer losal) {
        this.losal = losal;
    }

    public Integer getHisal() {
        return hisal;
    }

    public void setHisal(Integer hisal) {
        this.hisal = hisal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((grade == null) ? 0 : grade.hashCode());
        result = prime * result + ((losal == null) ? 0 : losal.hashCode());
        result = prime * result + ((hisal == null) ? 0 : hisal.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Salgrade sal = (Salgrade) obj;

        if ((grade != null) ? !grade.equals(sal.grade) : sal.grade != null) {
            return false;
        }
        if ((losal != null) ? !losal.equals(sal.losal) : sal.losal != null) {
            return false;
        }
        if ((hisal != null) ? !hisal.equals(sal.hisal) : sal.hisal != null) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Salgrade [grade=").append(grade).append(", losal=").append(losal).append(", hisal=").append(hisal)
                .append("]");
        return builder.toString();
    }

}


