package isa.avioCompany.model.dto;

import isa.avioCompany.model.Destination;
import lombok.Data;

@Data
public class PathDTO {

	private Long id;

	private Destination destinationStart;

	private Destination destinationEnd;

	private Integer numberOfTransfer;

	private Long avio_id;
}
