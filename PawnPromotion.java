public class PawnPromotion extends SpecialMove{

    Piece transformedPiece;

    public PawnPromotion(Cell start, Cell finish, Piece newPiece){
        super(start, finish);
        this.transformedPiece = newPiece;
    }

    public Piece getTransformedPiece(){
        return transformedPiece;
    }
}
