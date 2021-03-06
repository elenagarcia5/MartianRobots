package garcia.herrero.MartianRobots.dto;

import java.util.List;

import javax.validation.constraints.Size;

public class MartianRobotInputDto {

	@Size(min = 1)
	private List<String> lines;

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}
}

