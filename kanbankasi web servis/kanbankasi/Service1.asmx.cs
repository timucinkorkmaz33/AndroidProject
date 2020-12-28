using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.IO;
using System.Data.SqlClient;
using System.Data;
using System.Configuration;

namespace kanbankasi
{
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {
        [WebMethod]
        public int Kankayit(string name, string l_name, string sehir, string tel, string k_grubu)
        {

            SqlConnection connection = new SqlConnection();
            connection.ConnectionString = ConfigurationManager.ConnectionStrings["webconnectionstring"].ToString();
            connection.Open();
            try
            {

                SqlCommand cmd = new SqlCommand("INSERT INTO [kanbankasi] (name,l_name,sehir,tel,k_grubu) VALUES ('" + name + "','" + l_name + "','" + sehir + "','" + tel + "','" + k_grubu + "')", connection);
                cmd.ExecuteNonQuery();
                return 1;
            }

            catch (Exception ex)
            {
                return 0;
            }
            finally
            {

                connection.Close();

            }
        }

        [WebMethod]
        public int sil(string name, string l_name)
        {

            SqlConnection connection = new SqlConnection();
            connection.ConnectionString = ConfigurationManager.ConnectionStrings["webconnectionstring"].ToString();
            connection.Open();
            try
            {


                SqlCommand cmd = new SqlCommand("delete from [kanbankasi].[dbo].[kanbankasi] where name='"+name+"'AND l_name='"+l_name+"'", connection);
                cmd.ExecuteNonQuery();
                return 1;
            }

            catch (Exception ex)
            {
                return 0;
            }
            finally
            {

                connection.Close();

            }
        }
        
        
        [WebMethod]
        public string satiral(string k_grubu)
        {
            string sonuc = "";
            string[] dizi;

            string yol = ConfigurationManager.ConnectionStrings["webconnectionstring"].ToString();
            SqlConnection bag = new SqlConnection(yol);
            string sorgu = "select * from kanbankasi where k_grubu='" + k_grubu + "'";
            SqlDataAdapter sda = new SqlDataAdapter(sorgu, bag);
            DataSet ds = new DataSet();
            sda.Fill(ds);
            bag.Close();
            
          foreach(DataRow dr in ds.Tables[0].Rows)
            {sonuc+=dr[1].ToString()+";"+dr[2].ToString()+";"+dr[3].ToString()+";"+dr[4].ToString()+";"+dr[5].ToString();
                sonuc+="//";
            } 
               
            return sonuc; 
        }
    }
}
