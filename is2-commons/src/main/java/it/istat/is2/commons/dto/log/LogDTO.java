package it.istat.is2.commons.dto.log;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public final class LogDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5438016908034454972L;
	private int id;
	private String content;
	private Timestamp msgTime;
	private String type;

	private LogDTO(int id, String content, Timestamp msgTime, String type) {
		this.id = id;
		this.content = content;
		this.msgTime = msgTime;
		this.type = type;
	}

	private LogDTO() {
		super();
	}

	public String getMsg() {
		return this.content;

	}

}
