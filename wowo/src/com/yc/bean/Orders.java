package com.yc.bean;

import java.io.Serializable;

public class Orders implements Serializable{
	private static final long serialVersionUID = -6810077876219984669L;
	private String oid;
	private String dates;
	private Integer usid;
	private Integer gid;
	private Integer nums;
	private Double totalprice;
	private Integer status;
	
	private String uname;
	private String gname;
	private String flag;
	private String mark;
	
	public String toString() {
		return "Orders [oid=" + oid + ", dates=" + dates + ", usid=" + usid
				+ ", gid=" + gid + ", nums=" + nums + ", totalprice="
				+ totalprice + ", status=" + status + ", uname=" + uname
				+ ", gname=" + gname + ", flag=" + flag + ", mark=" + mark
				+ "]";
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getdates() {
		return dates;
	}
	public void setdates(String dates) {
		this.dates = dates;
	}
	public Integer getUsid() {
		return usid;
	}
	public void setUsid(Integer usid) {
		this.usid = usid;
	}
	public Integer getgid() {
		return gid;
	}
	public void setgid(Integer gid) {
		this.gid = gid;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public Integer getStatus() {
		return status;
	}
	public String getStatusStr() {
		if(status==0){
			return "已付款";
		}else if(status==1){
			return "已发货";
		}else{
			return "已签收";
		}	
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getgname() {
		return gname;
	}
	public void setgname(String gname) {
		this.gname = gname;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Orders() {
		super();
	}
	public Orders(String oid, String dates, Integer usid, Integer gid,
			Integer nums, Double totalprice, Integer status) {
		super();
		this.oid = oid;
		this.dates = dates;
		this.usid = usid;
		this.gid = gid;
		this.nums = nums;
		this.totalprice = totalprice;
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gname == null) ? 0 : gname.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((gid == null) ? 0 : gid.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((nums == null) ? 0 : nums.hashCode());
		result = prime * result + ((dates == null) ? 0 : dates.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((totalprice == null) ? 0 : totalprice.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (gname == null) {
			if (other.gname != null)
				return false;
		} else if (!gname.equals(other.gname))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (gid == null) {
			if (other.gid != null)
				return false;
		} else if (!gid.equals(other.gid))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (nums == null) {
			if (other.nums != null)
				return false;
		} else if (!nums.equals(other.nums))
			return false;
		if (dates == null) {
			if (other.dates != null)
				return false;
		} else if (!dates.equals(other.dates))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalprice == null) {
			if (other.totalprice != null)
				return false;
		} else if (!totalprice.equals(other.totalprice))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		return true;
	}
	
	
}
