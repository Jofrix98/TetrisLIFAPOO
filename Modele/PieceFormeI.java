package Modele;

public class PieceFormeI extends  Piece {

    public PieceFormeI(GrilleSimple _grille) {
        super(_grille);
        tabBool = new boolean[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }
        x = 5;
        y = 5;
        tabBool[0][2] = true;
        tabBool[1][2] = true;
        tabBool[2][2] = true;
        tabBool[3][2] = true;
    }

    public void rotation(){
        int[][] tabBoolint = new int[4][4];
        int[][] trans = new int[4][4];
        int[][] ihm = new int[4][4];
        int[][] res = new int[4][4];

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(tabBool[i][j]){
                    tabBoolint[i][j] = 1;
                }
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                trans[j][i] = tabBoolint[i][j] ;

            }
        }

        ihm[3][0] = 1;
        ihm[2][1] = 1;
        ihm[1][2] = 1;
        ihm[0][3] = 1;

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                res[i][j] = 0;
                for(int k=0; k<4 ;k++)
                {
                    res[i][j] += trans[i][k] * ihm[k][j];
                }
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                tabBool[i][j] = false;
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(res[i][j] == 1){
                    tabBool[i][j] = true;
                }
            }
        }

    }

}
