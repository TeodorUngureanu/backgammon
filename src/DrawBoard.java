
public class DrawBoard {
	
    public static void draw(Board b){
        StringBuilder sb = new StringBuilder();
        sb.append("-B01-B02-B03-B04-B05-B06-     -S07-S08-S09-S10-S11-S12-\n");
        for(int i=1; i<=5; i++){
            drawTopStoneLine(i,b,sb);
            sb.append("|\n");
        }
        drawTopNumberLines(b,sb);
        sb.append("|\n");
        sb.append("- - - - - - - - - - - - -     - - - - - - - - - - - - -\n");
        drawBottomNumberLines(b,sb);
        sb.append("|\n");
        for(int i=5; i>=1; i--){
            drawBottomStoneLine(i,b,sb);
            sb.append("|\n");
        }
        sb.append("-B24-B23-B22-B21-B20-B19-     -S18-S17-S16-S15-S14-S13-\n\n");
        System.out.print(sb);
    }

    private static void drawTopStoneLine(int i, Board b, StringBuilder sb) {
        for(int j=0; j<6; j++){
            drawSegment(i,j,b,sb);
        }
        sb.append("|  ");
        if(b.getNumberOfStolenDice(Stone.Color.WHITE) >= i){
            sb.append(Stone.WHITE);
        }else{
            sb.append(' ');
        }
        sb.append("  ");
        for(int j=6; j<12; j++){
            drawSegment(i,j,b,sb);
        }
    }
    
    private static void drawBottomStoneLine(int i, Board b, StringBuilder sb) {
        for(int j=23; j>17; j--){
            drawSegment(i,j,b,sb);
        }
        sb.append("|  ");
        if(b.getNumberOfStolenDice(Stone.Color.BLACK) >= i){
            sb.append(Stone.BLACK);
        }else{
            sb.append(' ');
        }
        sb.append("  ");
        for(int j=17; j>11; j--){
            drawSegment(i,j,b,sb);
        }
    }

    private static void drawTopNumberLines(Board b, StringBuilder sb) {
        for(int j=0; j<6; j++){
            drawSegmentNumberH(j,b,sb);
        }
        sb.append("|  ");
        if(b.getNumberOfStolenDice(Stone.Color.WHITE) >= 10){
            sb.append(1);
        }else if(b.getNumberOfStolenDice(Stone.Color.WHITE) > 5){
            sb.append(b.getNumberOfStolenDice(Stone.Color.WHITE));
        }else{
            sb.append(' ');
        }
        sb.append("  ");
        for(int j=6; j<12; j++){
            drawSegmentNumberH(j,b,sb);
        }
        sb.append("|\n");
        for(int j=0; j<6; j++){
            drawSegmentNumberL(j,b,sb);
        }
        sb.append("|  ");
        if(b.getNumberOfStolenDice(Stone.Color.WHITE) >= 10){
            sb.append(b.getNumberOfStolenDice(Stone.Color.WHITE)-10);
        }else{
            sb.append(' ');
        }
        sb.append("  ");
        for(int j=6; j<12; j++){
            drawSegmentNumberL(j,b,sb);
        }
    }

    private static void drawBottomNumberLines(Board b, StringBuilder sb) {
        for(int j=23; j>17; j--){
            drawSegmentNumberH(j,b,sb);
        }
        sb.append("|  ");
        if(b.getNumberOfStolenDice(Stone.Color.BLACK) >= 10){
            sb.append(1);
        }else if(b.getNumberOfStolenDice(Stone.Color.BLACK) > 5){
            sb.append(b.getNumberOfStolenDice(Stone.Color.BLACK));
        }else{
            sb.append(' ');
        }
        sb.append("  ");
        for(int j=17; j>11; j--){
            drawSegmentNumberH(j,b,sb);
        }
        sb.append("|\n");
        for(int j=23; j>17; j--){
            drawSegmentNumberL(j,b,sb);
        }
        sb.append("|  ");
        if(b.getNumberOfStolenDice(Stone.Color.BLACK) >= 10){
            sb.append(b.getNumberOfStolenDice(Stone.Color.BLACK)-10);
        }else{
            sb.append(' ');
        }
        sb.append("  ");
        for(int j=17; j>11; j--){
            drawSegmentNumberL(j,b,sb);
        }
    }

    private static void drawSegmentNumberH(int j, Board b, StringBuilder sb) {
        sb.append("| ");
        if(b.getStoneCount(j) >= 10){
            sb.append(1);
        }else if(b.getStoneCount(j) > 5){
            sb.append(b.getStoneCount(j));
        }else{
            sb.append(' ');
        }
        sb.append(" ");		
    }

    private static void drawSegmentNumberL(int j, Board b, StringBuilder sb) {
        sb.append("| ");
        if(b.getStoneCount(j) >= 10){
            sb.append(b.getStoneCount(j)-10);
        }else{
            sb.append(' ');
        }
        sb.append(" ");		
    }

    private static void drawSegment(int i, int j, Board b, StringBuilder sb) {
        sb.append("| ");
        if(b.getStoneCount(j) >= i){
            sb.append(b.getStone(j));
        }else{
            sb.append(' ');
        }
        sb.append(" ");
    }
	
}