public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;

	}
	public double calcDistance(Planet a){
		double r;
		r = Math.sqrt((a.xxPos - this.xxPos)*(a.xxPos - this.xxPos)+(a.yyPos - this.yyPos)*(a.yyPos - this.yyPos));
		// r = ((a.xxPos - this.xxPos)^2 +(a.yyPos - this.yyPos)^2)^{0.5};
		return r;

	}
	public double calcForceExertedBy(Planet a){
		double f;
		
		 f = G * a.mass * this.mass / (this.calcDistance(a)*this.calcDistance(a));

		return f;
	}

	public double calcForceExertedByX(Planet a){
		double f;
		f = this.calcForceExertedBy(a) * (a.xxPos-this.xxPos)/this.calcDistance(a);
		return f;
	}
	public double calcForceExertedByY(Planet a){
		double f;
		f = this.calcForceExertedBy(a)*(a.yyPos-this.yyPos)/this.calcDistance(a);
		return f;
	}

	public double calcNetForceExertedByX(Planet[] a){
		int i = 0;
		double f = 0;
		while(i < a.length){
			if(!this.equals(a[i])){
				f = f + this.calcForceExertedByX(a[i]);
			}	
			i++;
		}
		return f;
	}

	public double calcNetForceExertedByY(Planet[] a){
		int i = 0;
		double f = 0;
		while(i < a.length){
			if(!this.equals(a[i])){
				f = f + this.calcForceExertedByY(a[i]);
			}
			i++;
		}
		return f;
	}

	public void update(double dt, double fX, double fY){
		double ax = fX/mass;
		double ay = fY/mass;
		xxVel = xxVel + dt*ax;
		yyVel = yyVel + dt*ay;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}
	public void draw(){
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+imgFileName);
		
	}


}