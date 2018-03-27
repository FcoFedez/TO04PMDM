package pms.com.pmdp04_tofranciscofernandez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class ProductosActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private float lastX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        // seleccionar el ViewFlipper del layout anterior
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
    }
    // Evento asociado a tocar la pantalla
    // que mueve hacia un layout u otro (Windows o Linux)
    // http://developer.android.com/intl/es/reference/android/view/MotionEvent.html
    public boolean onTouchEvent(MotionEvent touchevent) {

        // Seleccionar
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                // detecta cuando el dedo presiona sobre la pantalla

                // obtener la coordenada X cuando se presiona sobre la pantalla
                lastX = touchevent.getX();
                break;

            case MotionEvent.ACTION_UP:
                // detecta cuando el dedo deja de presionar sobre la pantalla

                // obtener la coordenada X cuando se deja de presionar sobre la pantalla
                float currentX = touchevent.getX();

                // Hay que comprobar para donde deslizamos el dedo
                if (lastX < currentX) {
                    // Deslizamos de izquierda a derecha

                    // getDisplayedChild() devuelve el índice de la vista hija (siguiente pantalla)
                    // que se muestra actualmente, si es cero no hay pantalla anterior
                    // no podemos movernos a la derecha
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // Carga la animación para la pantalla que entra
                    // => en en este caso desde la izquierda
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);

                    // Carga la animación para la pantalla que sale
                    // => en en este caso hacia la derecha
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);

                    // Iniciar la animación para moverse a la pantalla anterior
                    // viewFlipper.showNext();
                    viewFlipper.showPrevious();
                }

                // Hay que comprobar para donde deslizamos el dedo
                if (lastX > currentX) {
                    // Deslizamos de derecha a izquierda

                    // getDisplayedChild() devuelve el índice de la vista hija (siguiente pantalla)
                    // que se muestra actualmente, si es la última hay pantalla siguiente
                    // no podemos movernos a la izquierda
                    /* if (viewFlipper.getDisplayedChild() == 1)
                        break;*/
                    if (viewFlipper.getDisplayedChild()==viewFlipper.getChildCount()-1)
                        break;

                    // Carga la animación para la pantalla que entra
                    // => en en este caso desde la derecha
                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);

                    // Carga la animación para la pantalla que sale
                    // => en en este caso hacia la izquierda
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);

                    // Iniciar la animación para moverse a la siguiente pantalla
                    //viewFlipper.showPrevious();
                    viewFlipper.showNext();
                }

                break;
        }
        return false;
    }
}
