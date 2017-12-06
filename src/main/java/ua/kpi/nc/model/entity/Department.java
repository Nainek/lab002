package ua.kpi.nc.model.entity;

import java.io.Serializable;

public class Department implements Serializable {

    private Long deptno;
    private String dname;
    private String loc;

    public static class Builder implements IBuilder<Department> {
            Department department = new Department();

            public Builder setDeptno(Long deptno) {
                department.deptno = deptno;
                return this;
            }

            public Builder setDname(String dname) {
                department.dname = dname;
                return this;
            }

            public Builder setLoc(String loc) {
            department.loc = loc;
            return this;
        }

        public Department build() {
            return department;
        }
    }

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
        result = prime * result + ((dname == null) ? 0 : dname.hashCode());
        result = prime * result + ((loc == null) ? 0 : loc.hashCode());
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

        Department department = (Department) obj;

        if ((deptno != null) ? !deptno.equals(department.deptno) : department.deptno != null) {
            return false;
        }
        if ((dname != null) ? !dname.equals(department.dname) : department.dname != null) {
            return false;
        }
        if ((loc != null) ? !loc.equals(department.loc) : department.loc != null) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Department [deptno=").append(deptno).append(", dname=").append(dname).append(", loc=").append(loc)
                .append("]");
        return builder.toString();
    }
}

