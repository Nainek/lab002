package ua.kpi.nc.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Employee implements Serializable {

    private Long empno;
    private String ename;
    private String job;
    private Timestamp hiredate;
    private Long mgr;
    private Integer sal;
    private Integer comm;
    private Long deptno;

    public static class Builder implements IBuilder<Employee> {
        Employee employee = new Employee();

        public Builder setEmpno(Long empno) {
            employee.empno = empno;
            return this;
        }

        public Builder setEname(String ename) {
            employee.ename = ename;
            return this;
        }

        public Builder setHiredate(Timestamp hiredate) {
            employee.hiredate = hiredate;
            return this;
        }

        public Builder setJob(String job) {
            employee.job = job;
            return this;
        }

        public Builder setMgr(Long mgr) {
            employee.mgr = mgr;
            return this;
        }

        public Builder setSal(Integer sal) {
            employee.sal = sal;
            return this;
        }

        public Builder setComm(Integer comm) {
            employee.comm = comm;
            return this;
        }

        public Builder setDeptno(Long deptno) {
            employee.deptno = deptno;
            return this;
        }

        public Employee build() {
            return employee;
        }
    }

    public Long getEmpno() {
        return empno;
    }

    public void setEmpno(Long empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Timestamp getHiredate() {
        return hiredate;
    }

    public void setHiredate(Timestamp hiredate) {
        this.hiredate = hiredate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getMgr() {
        return mgr;
    }

    public void setMgr(Long mgr) {
        this.mgr = mgr;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((empno == null) ? 0 : empno.hashCode());
        result = prime * result + ((ename == null) ? 0 : ename.hashCode());
        result = prime * result + ((job == null) ? 0 : job.hashCode());
        result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
        result = prime * result + ((mgr == null) ? 0 : mgr.hashCode());
        result = prime * result + ((sal == null) ? 0 : sal.hashCode());
        result = prime * result + ((comm == null) ? 0 : comm.hashCode());
        result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
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

        Employee employee = (Employee) obj;

        if ((empno != null) ? !empno.equals(employee.empno) : employee.empno != null) {
            return false;
        }
        if ((ename != null) ? !ename.equals(employee.ename) : employee.ename != null) {
            return false;
        }
        if ((job != null) ? !job.equals(employee.job) : employee.job != null) {
            return false;
        }
        if ((hiredate != null) ? !hiredate.equals(employee.hiredate) : employee.hiredate != null) {
            return false;
        }
        if ((mgr != null) ? !mgr.equals(employee.mgr) : employee.mgr != null) {
            return false;
        }
        if ((sal != null) ? !sal.equals(employee.sal) : employee.sal != null) {
            return false;
        }
        if ((comm != null) ? !comm.equals(employee.comm) : employee.comm != null) {
            return false;
        }
        if ((deptno != null) ? !deptno.equals(employee.deptno) : employee.deptno != null) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee [empno=").append(empno).append(", ename=").append(ename).append(", job=").append(job)
                .append(", hiredate=").append(hiredate).append(", mgr=").append(mgr).append(", sal=").append(sal)
                .append(", comm=").append(comm).append(", deptno=").append(deptno).append("]");
        return builder.toString();
    }
}
