package com.hongxin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fl_operation_diary")
public class OperationDiary implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int operationId;
	
    private String operationUserId;
	
    private String operationDate;
	
    private String operationCode;
	
    private String operationSource;
	
    private String exceptionDetail;
	
    private String description;
	
    private String oprationName;
	
    private String oprationIp;
	
    @Id
	@Column(name = "operation_id", unique = true, nullable = false)
    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }
    @Column(name = "operation_userId")
    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId == null ? null : operationUserId.trim();
    }
    @Column(name = "operation_date" )
    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate == null ? null : operationDate.trim();
    }
    @Column(name = "operation_code")
    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode == null ? null : operationCode.trim();
    }
    @Column(name = "operation_source")
    public String getOperationSource() {
        return operationSource;
    }

    public void setOperationSource(String operationSource) {
        this.operationSource = operationSource == null ? null : operationSource.trim();
    }
    @Column(name = "exception_detail")
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    @Column(name = "opration_name")
    public String getOprationName() {
        return oprationName;
    }

    public void setOprationName(String oprationName) {
        this.oprationName = oprationName == null ? null : oprationName.trim();
    }
    @Column(name = "opration_ip")
    public String getOprationIp() {
        return oprationIp;
    }

    public void setOprationIp(String oprationIp) {
        this.oprationIp = oprationIp == null ? null : oprationIp.trim();
    }
}