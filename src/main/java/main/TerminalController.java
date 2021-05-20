package main;

import config.ConfigData;
import config.ConfigManager;
import mqtt.GeneralMqttVariables;
import mqtt.MQTTReader;
import org.eclipse.paho.client.mqttv3.MqttException;
import sql.CulturaDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TerminalController {

	public static final String CURSOR = ">>> ";
	private static final String SAIR = "sair";
	private static final String AJUDA = "ajuda";
	private static final String READ = "lermqtt";
	private static final String PARAR = "parar";
	private static final String PARARTUDO = "parartudo";
	private static final String ALTERAR = "alterar";
	private static final String SHOWCONF = "mostrarconf";
	private static final String SHOWDEFAULT = "showdefault";
	private static final String SAVE = "salvar";
	private static final String RESETCONF = "reporconf";

	private static final String COMANDONAORECONHECIDO = "é um comando não reconhecido, por favor digitar \"ajuda\" para saber a lista de comandos.";
	private static final String ERRO_NO_INPUT_DA_INFORMACAO = "Erro no input da informação.";
	private static final String COPYRIGHT = "Programa feito no âmbito na disciplina de PSID pelo grupo 12.\n" +
			"Programa de terminal para gerir e sincronizar as importações do mongodb cloud para o local.\n" +
			"Escrever \"ajuda\" para ver os comandos disponiveis.";
	MQTTReader reader;

	ConfigData data;
	private final ConfigManager configManager;
	private boolean isImport = false;

	//public main.TerminalController(String filename,MongodbCloudCollector mongodbCloudCollector) throws FileNotFoundException {
	public TerminalController(String filename) throws FileNotFoundException {
		this.configManager = new ConfigManager(filename);
		data = configManager.readFromFile();
		//directOrMqtt();

	}

	public void launch() throws InterruptedException {
		System.out.println(COPYRIGHT);

		String autostart = data.getAutostart();
		if (autostart.equalsIgnoreCase("on")) {
			dispatchedReadMQTT();

		}

		String inputTerminal = "";
		while (!inputTerminal.equals(SAIR)) {
			try {
				System.out.print(CURSOR);
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				inputTerminal = reader.readLine().trim();
				handleInputTerminal(inputTerminal);
			} catch (IOException ioException) {
				System.out.println(ERRO_NO_INPUT_DA_INFORMACAO);
			} catch (Exception exception) {
				System.out.println("Erro inesperado");
			}
		}
        /*
        if(mongodbCloudCollector.isAlive()){
            mongodbCloudCollector.killWriters();
            mongodbCloudCollector.join();
        }
        */

		System.out.println("A sair do programa.");
	}

	private void handleInputTerminal(String inputTerminal) throws IOException {
		LinkedList<String> inputParts = new LinkedList<>(Arrays.asList(inputTerminal.split("-")));
		String command = inputParts.pop().trim();
		dispatcher(command, inputParts);
	}

	private void dispatcher(String command, LinkedList<String> commandArgs) {
		switch (command) {
			case SAIR:
			case "": {
				break;
			}
			case AJUDA: {
				dispatchedAjuda();
				break;
			}
			case READ: {
				dispatchedReadMQTT();
				break;

            } case PARAR: {
                dispatchedParar();
                break;
			}
			case ALTERAR: {
				dispatchedAlterar(commandArgs);
				break;
			}
			case SHOWCONF: {
				dispatchedShowconf();
				break;
			}
			case SHOWDEFAULT: {
				dispatchedShowDefault();
				break;
			}
			case RESETCONF: {
				dispatchedReset();
				break;
			}
			case SAVE: {
				dispatchedSave();
				break;
			}
			default: {
				System.out.println(command + " " + COMANDONAORECONHECIDO);
			}
		}
	}

	/*
		private void dispatchedRestart() {
			mongodbCloudCollector.killWriters();
			mongodbCloudCollector.interrupt();
			mongodbCloudCollector.start();
		}

	*/
	private void dispatchedReset() {
		this.data = new ConfigData();
		System.out.println("Novas configurações:\n" + this.data);
	}

	private void dispatchedShowDefault() {
		System.out.println(new ConfigData());
	}

	private void dispatchedSave() {
		configManager.writeToFile(this.data);
		System.out.println("Configurações salvas em " + configManager.getFilename());
	}

	private void dispatchedShowconf() {
		System.out.println(data);
	}

	private void dispatchedReadMQTT() {
		try {
			if (!isImport) {
				System.out.println("A inciar leitura e sincronização.");
				isImport = true;
				//String clone_mode = data.getClone_mode();
				Connection connection = CulturaDB.getLocalConnection();
				reader = new MQTTReader(data, GeneralMqttVariables.CLIENT_ID, GeneralMqttVariables.PERSISTENCE, connection);
				reader.initialize();
				//mongodbCloudCollector.start();
			} else {
				System.out.println("A leitura já está a ser executada, por favor pare para voltar a iniciar a importação.");
			}
		} catch (MqttException mqttException) {
			//mqttException.printStackTrace();
            System.out.println("Problemas a inicializar a leitura do servidor MQTT ");
		} catch (SQLException sqlException) {
            //sqlException.printStackTrace();
            System.out.println("Problemas com a conecção á BD local SQL");

        }
    }

		private void dispatchedParar() {
			try {
				reader.stop();
				isImport = false;
			} catch (MqttException mqttException) {
				System.out.println("Problemas a parar o mqttreader!");
			}
		}

	/*
		private void dispatchedPararALL() {
			mongodbCloudCollector.killWriters();
			isImport = false;
			System.out.println("Todos os cloners foram parados com sucesso.");
		}
	*/
	private void dispatchedAlterar(List<String> alterarNewValue) {
		for (String changeParam : alterarNewValue) {
			LinkedList<String> args = new LinkedList<>(Arrays.asList(changeParam.split(" ")));
			String field = args.pop();

			if (data.changeSetting(field, args)) {
				System.out.println("Foi alterado o campo: " + field);
			}
		}
	}

	//TODO atualizar
	private void dispatchedAjuda() {
		System.out.println("Lista de comandos:\n" +
				"\"" + READ + "\": Importar e sincronizar as mongodb locais com o cloud.\n" +
				"\"" + PARAR + "\": Parar de ler do MQTT . exemplo:\" parar \".\n" +
				"\"" + ALTERAR + "\": Alterar parametros da configuração. exemplo: \"alterar -mqttqos 1\".\n" +
				"\"" + SHOWCONF + "\": Mostrar configurações atuais.\n" +
				"\"" + SHOWDEFAULT + "\": Mostrar configurações default.\n" +
				"\"" + SAVE + "\": Salvar as configurações atuais.\n" +
				"\"" + RESETCONF + "\": Carregar as configurações default\n" +
				"\"" + SAIR + "\": Sair do programa.\n" +
				"Para as novas configurações tomarem efeito sugerimos parar o programa e voltar a abrir"
		);

	}


}