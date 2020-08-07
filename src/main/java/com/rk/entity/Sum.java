package com.rk.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 描述： （sum）
 * 
 * 作者： gjmctp
 * 时间： 2020-08-05 14:01:29
 */
 public class Sum implements Serializable {
 
	 private static final long serialVersionUID = 1L;
	 	
		
		/**  */
		private int sumId;
			
		/**  */
		private int count;
			
		/**  */
		private String standby1;
			
		/**  */
		private String standby2;
			
		/**  */
		private Date makeDate;
			
		/**  */
		private String makeTime;
			
		/**  */
		private Date modifyDate;
			
		/**  */
		private String modifyTime;
			
		
		public int getSumId() {
			return sumId;
		}

		public void setSumId(int sumId) {
			this.sumId = sumId;
		}
		
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		public String getStandby1() {
			return standby1;
		}

		public void setStandby1(String standby1) {
			this.standby1 = standby1;
		}
		
		public String getStandby2() {
			return standby2;
		}

		public void setStandby2(String standby2) {
			this.standby2 = standby2;
		}
		
		public Date getMakeDate() {
			return makeDate;
		}

		public void setMakeDate(Date makeDate) {
			this.makeDate = makeDate;
		}
		
		public String getMakeTime() {
			return makeTime;
		}

		public void setMakeTime(String makeTime) {
			this.makeTime = makeTime;
		}
		
		public Date getModifyDate() {
			return modifyDate;
		}

		public void setModifyDate(Date modifyDate) {
			this.modifyDate = modifyDate;
		}
		
		public String getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(String modifyTime) {
			this.modifyTime = modifyTime;
		}
			
 }