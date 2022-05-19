package org.xtext.example.mydsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'\"{\"\"tracked_at\"\":'", "',\"\"value\"\":'", "'}\"'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_INT=4;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalMyDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMyDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMyDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMyDsl.g"; }



     	private MyDslGrammarAccess grammarAccess;

        public InternalMyDslParser(TokenStream input, MyDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected MyDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalMyDsl.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalMyDsl.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalMyDsl.g:65:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMyDsl.g:71:1: ruleModel returns [EObject current=null] : ( (lv_datas_0_0= ruledata ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_datas_0_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:77:2: ( ( (lv_datas_0_0= ruledata ) )* )
            // InternalMyDsl.g:78:2: ( (lv_datas_0_0= ruledata ) )*
            {
            // InternalMyDsl.g:78:2: ( (lv_datas_0_0= ruledata ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:79:3: (lv_datas_0_0= ruledata )
            	    {
            	    // InternalMyDsl.g:79:3: (lv_datas_0_0= ruledata )
            	    // InternalMyDsl.g:80:4: lv_datas_0_0= ruledata
            	    {

            	    				newCompositeNode(grammarAccess.getModelAccess().getDatasDataParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_datas_0_0=ruledata();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getModelRule());
            	    				}
            	    				add(
            	    					current,
            	    					"datas",
            	    					lv_datas_0_0,
            	    					"org.xtext.example.mydsl.MyDsl.data");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuledata"
    // InternalMyDsl.g:100:1: entryRuledata returns [EObject current=null] : iv_ruledata= ruledata EOF ;
    public final EObject entryRuledata() throws RecognitionException {
        EObject current = null;

        EObject iv_ruledata = null;


        try {
            // InternalMyDsl.g:100:45: (iv_ruledata= ruledata EOF )
            // InternalMyDsl.g:101:2: iv_ruledata= ruledata EOF
            {
             newCompositeNode(grammarAccess.getDataRule()); 
            pushFollow(FOLLOW_1);
            iv_ruledata=ruledata();

            state._fsp--;

             current =iv_ruledata; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuledata"


    // $ANTLR start "ruledata"
    // InternalMyDsl.g:107:1: ruledata returns [EObject current=null] : (otherlv_0= '\"{\"\"tracked_at\"\":' ( (lv_tracked_1_0= RULE_INT ) ) otherlv_2= ',\"\"value\"\":' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= '}\"' ) ;
    public final EObject ruledata() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_tracked_1_0=null;
        Token otherlv_2=null;
        Token lv_value_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalMyDsl.g:113:2: ( (otherlv_0= '\"{\"\"tracked_at\"\":' ( (lv_tracked_1_0= RULE_INT ) ) otherlv_2= ',\"\"value\"\":' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= '}\"' ) )
            // InternalMyDsl.g:114:2: (otherlv_0= '\"{\"\"tracked_at\"\":' ( (lv_tracked_1_0= RULE_INT ) ) otherlv_2= ',\"\"value\"\":' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= '}\"' )
            {
            // InternalMyDsl.g:114:2: (otherlv_0= '\"{\"\"tracked_at\"\":' ( (lv_tracked_1_0= RULE_INT ) ) otherlv_2= ',\"\"value\"\":' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= '}\"' )
            // InternalMyDsl.g:115:3: otherlv_0= '\"{\"\"tracked_at\"\":' ( (lv_tracked_1_0= RULE_INT ) ) otherlv_2= ',\"\"value\"\":' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= '}\"'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getDataAccess().getTracked_atKeyword_0());
            		
            // InternalMyDsl.g:119:3: ( (lv_tracked_1_0= RULE_INT ) )
            // InternalMyDsl.g:120:4: (lv_tracked_1_0= RULE_INT )
            {
            // InternalMyDsl.g:120:4: (lv_tracked_1_0= RULE_INT )
            // InternalMyDsl.g:121:5: lv_tracked_1_0= RULE_INT
            {
            lv_tracked_1_0=(Token)match(input,RULE_INT,FOLLOW_5); 

            					newLeafNode(lv_tracked_1_0, grammarAccess.getDataAccess().getTrackedINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDataRule());
            					}
            					setWithLastConsumed(
            						current,
            						"tracked",
            						lv_tracked_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getDataAccess().getValueKeyword_2());
            		
            // InternalMyDsl.g:141:3: ( (lv_value_3_0= RULE_INT ) )
            // InternalMyDsl.g:142:4: (lv_value_3_0= RULE_INT )
            {
            // InternalMyDsl.g:142:4: (lv_value_3_0= RULE_INT )
            // InternalMyDsl.g:143:5: lv_value_3_0= RULE_INT
            {
            lv_value_3_0=(Token)match(input,RULE_INT,FOLLOW_6); 

            					newLeafNode(lv_value_3_0, grammarAccess.getDataAccess().getValueINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDataRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getDataAccess().getRightCurlyBracketQuotationMarkKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruledata"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});

}