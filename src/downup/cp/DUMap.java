package downup.cp;

import java.io.File;
import java.util.ArrayList;

import downup.IOTools;

public enum DUMap {

	SNOWTOP("koth_snowtop", "SnowTop");
	
	private String mapFile, name;
	private ArrayList<ControlPoint> cpList = new ArrayList<ControlPoint>();
	private XYZPoint redSpawn = new XYZPoint(0, 0, 0), bluSpawn = new XYZPoint(0, 0, 0);
	DUMap(String mapFile, String name) {
		this.mapFile = mapFile;
		this.name = name;
		ArrayList<String> data = IOTools.readFile(new File("cakedata/" + mapFile + ".txt"));
		for (String a : data) {
			String[] v = a.split(";");
			if (v[0].equals("cp")) {
				// ID = [1]
				int id = Integer.parseInt(v[1]);
				// Location (x,y,z) = [2],[3],[4]
				double x = Double.parseDouble(v[2]), y = Double.parseDouble(v[3]), z = Double.parseDouble(v[4]);
				// Owner = [5]
				int owner = Integer.parseInt(v[5]);
				// Map = this;
				DUMap map = this;
				cpList.add(new ControlPoint(id, x,y,z, owner, map));
			} else if (v[0].equals("blu")) {
				// Location (x,y,z) = [1],[2],[3]
				
				bluSpawn.x = Double.parseDouble(v[1]);
				bluSpawn.y = Double.parseDouble(v[2]);
				bluSpawn.z = Double.parseDouble(v[3]);
			} else if (v[0].equals("red")) {
				// Location (x,y,z) = [1],[2],[3]
				
				redSpawn.x = Double.parseDouble(v[1]);
				redSpawn.y = Double.parseDouble(v[2]);
				redSpawn.z = Double.parseDouble(v[3]);
			}
		}
	}
	
	public String getMapFile() {
		return mapFile;
	}
	
	public String getName() {
		return name;
	}

	public static DUMap getByName(String string) {
		for (DUMap map : DUMap.values()) {
			if (map.name.equals(string)) {
				return map;
			}
		}
		return null;
	}

	public XYZPoint getBLUSpawn() {
		return bluSpawn;
	}
	
	public XYZPoint getREDSpawn() {
		return redSpawn;
	}
	
	public ArrayList<ControlPoint> getControlPoints() {
		return cpList;
	}
}
