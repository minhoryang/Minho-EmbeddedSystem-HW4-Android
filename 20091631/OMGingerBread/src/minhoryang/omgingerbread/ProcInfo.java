package minhoryang.omgingerbread;

public class ProcInfo{
	public int user;
	public int nice;
	public int system;
	public int idle;
	
	public Double getPercent(ProcInfo before){
		int _user = this.user - before.user;
		int _nice = this.nice - before.nice;
		int _system = this.system - before.system;
		int _idle = this.idle - before.idle;
		Double ret = ((double)1.0 - (double)_idle / (double)(_user + _nice + _system + _idle)) * 100;
		return ret;
	}
}
