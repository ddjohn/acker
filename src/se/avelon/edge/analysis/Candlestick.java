package se.avelon.edge.analysis;

import se.avelon.edge.annotations.Requirement;

@Requirement (tag = "Terminology.001" )
public class Candlestick {
	public enum CandlestickEnum {
		GreenMarubozu,
		GreenOpeningMarubozu,
		GreenClosingMarubozu,
		GreenLongCandle,
		GreenHammer,
		DragonflyDoji,
		RedHammer,

		Neutral,

		RedMarubozu,
		RedOpeningMarubozu,
		RedClosingMarubozu,
		RedLongCandle,
		RedShootingStar,
		GravestoneDoji,
		GreenShootingStar,
		
		Unknown		
	}

	public static String candlestickText[] = new String[] {
		"GreenMarubozu",
		"GreenOpeningMarubozu",
		"GreenClosingMarubozu",
		"GreenLongCandle",
		"GreenHammer",
		"DragonflyDoji",
		"RedHammer",

		"Neutral",

		"RedMarubozu",
		"RedOpeningMarubozu",
		"RedClosingMarubozu",
		"RedLongCandle",
		"RedShootingStar",
		"GravestoneDoji",
		"GreenShootingStar",
		
		"-"		
	};
	
	@Requirement (tag = "Analysis.001" )
	public static CandlestickEnum analyze(double op, double lp, double hp, double cp)  {
		assert(hp >= lp);
		assert(cp <= hp && cp >= lp);
		assert(op <= hp && op >= lp);

		double half      = lp + (hp-lp)/2;
		double onethird  = lp + (hp-lp)/3;
		double twothirds = hp - (hp-lp)/3;
		double low       = lp + (hp-lp)*0.05;
		double high      = hp - (hp-lp)*0.05;

		//System.out.println("half=" + half + ",onethird=" + onethird + ",twothirds=" + twothirds + ",low=" + low + ",high=" + high);

		// Neutral
		if(op > onethird && op < twothirds && cp > onethird && op < twothirds) {
			return CandlestickEnum.Neutral;
		}

		if(op >= high && cp >= high) return CandlestickEnum.DragonflyDoji;
		if(op <= low  && cp <= low)  return CandlestickEnum.GravestoneDoji;

		// Green
		if(cp > op) {
			if(op <= low  && cp >= high)  return CandlestickEnum.GreenMarubozu;
			if(op <= low  && cp >= half)  return CandlestickEnum.GreenOpeningMarubozu;
			if(op <= half && cp >= high) return CandlestickEnum.GreenClosingMarubozu;
			if(op <= half && cp >= half) return CandlestickEnum.GreenLongCandle;
			if(op >= half && cp >= high)  return CandlestickEnum.GreenHammer;
			if(op <= low  && cp <= half)  return CandlestickEnum.GreenShootingStar;
		}

		// Red
		if(op > cp) {
			if(op >= high && cp <= low)  return CandlestickEnum.RedMarubozu;
			if(op >= high && cp <= half) return CandlestickEnum.RedOpeningMarubozu;
			if(op >= half && cp <= low)  return CandlestickEnum.RedClosingMarubozu;
			if(op >= half && cp <= half) return CandlestickEnum.RedLongCandle;
			if(op >= high && cp >= half) return CandlestickEnum.RedHammer;
			if(op <= half && cp <= low)  return CandlestickEnum.GreenShootingStar;
		}
		return CandlestickEnum.Unknown;
	}	

	public static String lookup(CandlestickEnum e) {
		return candlestickText[e.ordinal()];
	}
}
