package config;

import mqtt.GeneralMqttVariables;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;



public class ConfigData {
	private String mqttbroker;
	private int mqttqos;
	private String mqtttopic;
	private String autostart;

	public String getMqttbroker() {
		return mqttbroker;
	}
	public ConfigData(
			String mqttbroker,
			int mqttqos,
			String mqtttopic,
			String autostart
	) {
		this.mqttbroker = mqttbroker;
		this.mqttqos = mqttqos;
		this.mqtttopic = mqtttopic;
		this.autostart = autostart;
	}

	public ConfigData() {
		this.mqttbroker = GeneralMqttVariables.BROKER;
		this.mqttqos = GeneralMqttVariables.QOS;
		this.mqtttopic = GeneralMqttVariables.TOPIC;
		this.autostart = "off";
	}

	public void         setMqttbroker(String mqttbroker) {	    this.mqttbroker = mqttbroker;	  }
	public int          getMqttqos() {		                    return mqttqos;	                  }
	public void         setMqttqos(int mqttqos) {   	        this.mqttqos = mqttqos;	          }
	public String       getMqtttopic() {		                return mqtttopic;	              }
	public void         setMqtttopic(String mqtttopic) {        this.mqtttopic = mqtttopic;	      }
	public String       getAutostart() {                        return autostart;                 }
    public void         setAutostart(String autostart) {        this.autostart = autostart;       }


	/**
	 * Method To change Config Settings
	 */
	public boolean changeSetting(String setting2Change, List<String> newValue) {
		String[] newArrayValue = newValue.toArray(new String[0]);
		String newSingleValue = "";
		if(!newValue.isEmpty()){
			for(String value: newValue) {
				newSingleValue+=value;
				newSingleValue+=" ";
			}
			newSingleValue = newSingleValue.substring(0,newSingleValue.length()-1);

			//O switch case nao ta a igualar as strings, se em caso de houver uma resolução a isso trocar de volta para o switch case

			setting2Change = setting2Change.toLowerCase(Locale.ROOT);

			if(setting2Change.equalsIgnoreCase(config.ConfigParams.AUTOSTART.getLabel()))
				setAutostart(newSingleValue);
			else if(setting2Change.equalsIgnoreCase(config.ConfigParams.MQTTBROKER.getLabel()))
				setAutostart(newSingleValue);
			else if(setting2Change.equalsIgnoreCase(config.ConfigParams.MQTTQOS.getLabel())) {
				try{
					setMqttqos(Integer.parseInt(newSingleValue));
				} catch (NumberFormatException e) {
					System.out.println("Não foi dado um número");
					return false;
				}
			} else if(setting2Change.equalsIgnoreCase(config.ConfigParams.MQTTTOPIC.getLabel()))
				setMqtttopic(newSingleValue);
			else {
				System.out.println("Parametro de configuração desconhecido");
				return false;
			}
			return true;
		}
		System.out.println("Valor vazio");
		return false;
	}

	@Override
	public String toString() {
		String spacer = "    ";
		return "MongodbCloudCollectorData{" +
				"\n  " + spacer + ConfigParams.AUTOSTART      +"='" + autostart                     + '\'' +
				"\n  " + spacer + ConfigParams.MQTTBROKER     +"='" + mqttbroker                    + '\'' +
				"\n  " + spacer + ConfigParams.MQTTQOS        +"='" + mqttqos                       + '\'' +
				"\n  " + spacer + ConfigParams.MQTTTOPIC      +"='" + mqtttopic                     + '\'' +
				"\n}";
	}
}
