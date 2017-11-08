
package Integral;
/**
 *
 * @author SERINAC V.G
 */
public class ClsPolinomio {
    
    public int _grado;
    public double [] _a;
    
    //Constructores

    public ClsPolinomio(int grado)
    {
        _grado=grado;
        _a=new double [_grado];
    }
    
    public ClsPolinomio(int grado, double[]coef)
    {
        _grado=grado;
        _a=coef;
    }
    
    public ClsPolinomio(ClsPolinomio t)
    {
        _grado=t._grado;
        _a=t._a;
    }
    
    //Métodos Set
    
    public void grado(int valor)
    {
        _grado=valor;
    }
    
    public void a(double[] coef)
    {
        _a=coef;
    }

    public void a(int i, float valor)
    {
        _a[i]=valor;
    }
    
    //Métodos Get
    
    public int grado()
    {
        return _grado;
    }
    
    public double[] a()
    {
        return _a;
    }
    
    public double a(int i)
    {
        return _a[i];
    }
    
    
    //Método de Horner
    
    public double Horner(float valx)
    {
        double valor = (_a[_grado-2] + _a[_grado-1])*valx;
        for (int i=_grado-3; i>=0; i--)
        {
            valor = _a[i] + (valor*valx); 
        }
        return valor;
    }
    
    //Integral Método Rectángulo izquierdo
    
    public double izquierdo(float a, float b, int n)
    {
        double deltax = (b-a)/(double)(n);
        double suma = 0;
        for(int i=0; i<n; i++)
        {
            suma+= Horner((float)(a+i*deltax));
        }
        return deltax*suma;
    }
    
    //Integral Método Rectángulo Derecho
    
     public double derecho(float a, float b, int n)
    {
        double deltax = (b-a)/(double)(n);
        double suma = 0;
        for(int i=1; i<=n; i++)
        {
            suma+= Horner((float)(a+i*deltax));
        }
        return deltax*suma;
    }
    
    //Integral Metodo Trapecio
            
    public double trapecio(float a, float b, int n)
    {
        return (izquierdo(a,b,n) + derecho(a,b,n))/2;
    }
    
    //Integral Método Simpson 1/3
    
    public double simpson(float a, float b, int n)
    {
        if(n%2!=0)
        {
            n+=1;
        }
        double deltax = (b-a)/(double)(n);
        double sumpar = 0;
        double sumimpar = 0;
        for(int i=1; i<n; i+=2)
        {
            sumimpar+= Horner((float)(a+i*deltax));
        }
        for(int i=2; i<n-1; i+=2)
        {
            sumpar+= Horner((float)(a+i*deltax));
        }
        return (deltax/3)*(Horner(a)*Horner(b)+4*sumimpar+2+sumpar);
    }
    
    //Metodo Ver
    
    public String ver()
    {
        String polinomio="";
    
        for(int i=_grado-1;i>0;i--)
        {
            if(_a[i]!=0)
            {
                polinomio+= _a[i]+"*"+"X^"+i+" ";
            }
            if(_a[i-1]>0)
            {
                polinomio+="+";
            }
        }
        polinomio+=_a[0];
        return polinomio;
    
    }
}
