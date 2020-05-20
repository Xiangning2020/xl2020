public class NBody{	
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] bodys = readBodies(filename);
		//System.out.println(readRadius(filename));


		// set the scale so that it matches the radius of the universe.
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-r,r);

		//draw the image of the universe;
		String backround = "images/starfield.jpg";
		StdDraw.clear();
		StdDraw.picture(0,0,backround);

		for(int i = 0 ; i < bodys.length; i++){
			bodys[i].draw();
		}

		StdDraw.show();

		double time = 0;
		while(time < T){
			double[] xForces = new double[bodys.length];
			double[] yForces = new double[bodys.length];
			for(int i = 0; i < bodys.length; i++){
				xForces[i] = bodys[i].calcNetForceExertedByX(bodys);
				yForces[i] = bodys[i].calcNetForceExertedByY(bodys);
			}

			for(int i = 0 ;i < bodys.length; i++){
				bodys[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,backround);
			
			for(int i = 0 ; i < bodys.length; i++){
				bodys[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;

		}
		StdOut.printf("%d\n", bodys.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < bodys.length; i++) {
   		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodys[i].xxPos, bodys[i].yyPos, bodys[i].xxVel,
                  bodys[i].yyVel, bodys[i].mass, bodys[i].imgFileName);   
		}


	}

	// @Deprecated
	public static double readRadius(String filename){
		if(filename.length() == 0){
			System.out.println("Please enter a valid name of a file.");
		}
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Planet[] readBodies(String filename){
		if(filename.length() == 0){
			System.out.println("Please enter a valid name of the file.");
		}
		In in = new In(filename);
		// the nume of the plane, use it in the later loop;
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] bodyarray = new Planet[num];
		for(int i = 0; i < num; i++){
			double xp = in.readDouble();
			double yp = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double m = in.readDouble();
			String name = in.readString();
			// 下面这个地方需要用到 new，因为是新定义的一个body，而不是原来就有的body。所以还是需要new/
			bodyarray[i] = new Planet(xp,yp,xv,yv,m,name); 

		}
		return bodyarray;

	}

}